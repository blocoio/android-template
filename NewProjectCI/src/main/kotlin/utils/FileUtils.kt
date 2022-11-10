package utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.FileFilterUtils
import org.apache.commons.io.filefilter.TrueFileFilter
import java.io.*

fun File.findAndReplaceContent(content: String, replacement: String): Boolean {
    val newTempFile = File(parentFile.absolutePath, "temp")

    FileReader(this).use { fileReader ->
        val fileText = fileReader.readText()
        if (fileText.contains(content, ignoreCase = true)) {
            newTempFile.createNewFile()

            FileWriter(newTempFile).use { fileWriter ->
                fileWriter.write(fileText.replace(content, replacement, ignoreCase = true))
                fileWriter.flush()
            }
        }
    }

    // Replace old File For new One
    if (newTempFile.exists()) {
        newTempFile.copyTo(this, true)
        newTempFile.delete()
        return true
    }

    return false
}


@Throws(IOException::class)
fun File.copyDirectory(destDir: File): Pair<Int, MutableSharedFlow<String>> {
    if (!isDirectory) {
        throw IOException("Source '$this' exists but is not a directory")
    } else if (canonicalPath == destDir.canonicalPath) {
        throw IOException("Source '$this' and destination '$destDir' are the same")
    } else {
        val fileCopyFlow = MutableSharedFlow<String>()
        val numberOfFiles = FileUtils.listFiles(
            this,
            FileFilterUtils.trueFileFilter(),
            TrueFileFilter.INSTANCE
        ).size

        CoroutineScope(Dispatchers.IO).launch {
            this@copyDirectory.doCopyDirectory(destDir, fileCopyFlow)
        }

        return Pair(numberOfFiles, fileCopyFlow)
    }
}

@Throws(IOException::class)
private suspend fun File.doCopyDirectory(
    destDir: File,
    fileCopyFlow: MutableSharedFlow<String>
) {
    val srcFiles = listFiles() ?: throw IOException("Failed to list contents of $this")

    if ((destDir.exists() && !destDir.isDirectory) ||
        (!destDir.mkdirs() && !destDir.isDirectory) ||
        !destDir.canWrite()
    ) {
        throw IOException("Destination '$destDir' is invalid")
    }

    val numberOfFiles = srcFiles.size
    for (fileNumber in 0 until numberOfFiles) {
        val srcFile = srcFiles[fileNumber]
        val dstFile = File(destDir, srcFile.name)
        if (srcFile.isDirectory) {
            srcFile.doCopyDirectory(dstFile, fileCopyFlow)
        } else {
            FileUtils.copyFile(srcFile, dstFile)
            fileCopyFlow.emit("Copying ${srcFile.absolutePath} -> ${dstFile.absolutePath}")
        }
    }
}

