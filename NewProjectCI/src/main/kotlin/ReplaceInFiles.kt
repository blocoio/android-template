import NewProjectCI.Companion.OLD_APPNAME
import NewProjectCI.Companion.OLD_PACKAGE
import NewProjectCI.Companion.OLD_TITLE
import kotlinx.coroutines.flow.flow
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.FileFilterUtils
import org.apache.commons.io.filefilter.TrueFileFilter
import utils.Logger
import utils.findAndReplaceContent
import java.io.File

suspend fun NewProjectCI.replaceInFiles(ignoreFiles: List<String> = listOf(".*\\.yml", ".*\\.bin")) = flow {
        // Search and replace in files
        FileUtils.iterateFiles(File("../$title"), FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE)
            .forEach {file ->
                if (ignoreFiles.find { file.name.matches(it.toRegex())}.isNullOrEmpty()) {
                    var logMessage = ""
                    if(file.findAndReplaceContent(OLD_APPNAME, title)) {
                        logMessage = "Replaced $OLD_APPNAME for $title in ${file.absolutePath}"
                    }
                    if(file.findAndReplaceContent(OLD_PACKAGE, packageName)) {
                        if (logMessage.isNotEmpty()) logMessage += "\n"
                        logMessage += "Replaced $OLD_PACKAGE for $packageName in ${file.absolutePath}"
                    }
                    if(file.findAndReplaceContent(OLD_TITLE, title)) {
                        logMessage = "Replaced $OLD_TITLE for $title in ${file.absolutePath}"
                    }

                    emit(logMessage)
                }

            }
    }