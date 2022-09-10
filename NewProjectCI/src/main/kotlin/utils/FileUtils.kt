package utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        val numberOfFiles = FileUtils.listFiles(this, FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE).size

        CoroutineScope(Dispatchers.IO).launch {
            this@copyDirectory.doCopyDirectory(destDir, fileCopyFlow)
        }

        return Pair(numberOfFiles, fileCopyFlow)
    }
}

@Throws(IOException::class)
suspend fun File.doCopyDirectory(
    destDir: File,
    fileCopyFlow: MutableSharedFlow<String>
) {
    withContext(Dispatchers.IO) {
        val srcFiles = listFiles()
        if (srcFiles == null) {
            throw IOException("Failed to list contents of $this")
        } else {
            if (destDir.exists()) {
                if (!destDir.isDirectory) {
                    throw IOException("Destination '$destDir' exists but is not a directory")
                }
            } else if (!destDir.mkdirs() && !destDir.isDirectory) {
                throw IOException("Destination '$destDir' directory cannot be created")
            }
            if (!destDir.canWrite()) {
                throw IOException("Destination '$destDir' cannot be written to")
            } else {
                val numberOfFiles = srcFiles.size
                for (fileNumber in 0 until numberOfFiles) {
                    val srcFile = srcFiles[fileNumber]
                    val dstFile = File(destDir, srcFile.name)
                        if (srcFile.isDirectory) {
                            srcFile.doCopyDirectory(dstFile, fileCopyFlow)
                        } else {
                            fileCopyFlow.emit("Copying ${srcFile.absolutePath} -> ${dstFile.absolutePath}")
                            FileUtils.copyFile(srcFile, dstFile)
                        }
                }
            }
        }
    }
}
