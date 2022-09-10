import NewProjectCI.Companion.OLD_APPNAME
import NewProjectCI.Companion.OLD_PACKAGE
import kotlinx.coroutines.flow.flow
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.FileFilterUtils
import org.apache.commons.io.filefilter.TrueFileFilter
import utils.findAndReplaceContent
import java.io.File

fun NewProjectCI.replaceInFiles() = flow {
        // Search and replace in files
        FileUtils.iterateFiles(File("./$title"), FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE)
            .forEach {
                var logMessage = ""
                if(it.findAndReplaceContent(OLD_APPNAME, title)) {
                    logMessage = "Replaced $OLD_APPNAME for $title in ${it.absolutePath}"
                }
                if(it.findAndReplaceContent(OLD_PACKAGE, packageName)) {
                    if (logMessage.isNotEmpty()) logMessage += "\n"
                    logMessage += "Replaced $OLD_PACKAGE for $packageName in ${it.absolutePath}"
                }

                emit(logMessage)
            }
    }