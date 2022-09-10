import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import me.tongfei.progressbar.ProgressBar
import utils.Logger.Companion.logger
import java.lang.Thread.*

class NewProjectCI : CliktCommand() {

    private val packageNameRegex = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_]\$".toRegex()
    val packageName: String by option(help="new package name (i.e. com.example.package)")
        .prompt("Package name")
        .check("(i.e. com.example.package)") { it.matches(packageNameRegex) }

    private val titleRegex = "\\w+".toRegex()
    val title: String by option(help="new app title (i.e. MyApp)")
        .prompt("App name")
        .check("(i.e. MyApp)") { it.matches(titleRegex) }

    private val verbose by option().flag()

    override fun run() = runBlocking {
        logger.enabled = verbose

        ProgressBar("New Project", 100).use { pb ->
            pb.extraMessage = "Coping template"
            sleep(100)
            val (numberOfFilesToCopy, stateOfCopiedFiles) = copyMainTemplate()
            var stepBy: Long = (numberOfFilesToCopy/20).toLong()
            var i = 0
            stateOfCopiedFiles.take(numberOfFilesToCopy).collect {
                logger.d(it)
                i++
                if (i >= stepBy) {
                    pb.step()
                    i = 0
                }
            }

            pb.extraMessage = "Cleaning folders"
            sleep(100)
            val (numberOfOperations, flowFolders) = cleanProject()
            stepBy = (20/numberOfOperations).toLong()
            flowFolders.collect {
                logger.d(it)
                pb.stepBy(stepBy)
            }


            pb.extraMessage = "Renaming Packages"
            sleep(100)
            val (numberOfSourceSets, flowRenaming) = renamePackages()
            stepBy = (20/numberOfSourceSets).toLong()
            flowRenaming.collect {
                logger.d(it)
                pb.stepBy(stepBy)
            }

            pb.extraMessage = "Replacing data in files"
            sleep(100)
            replaceInFiles().collect {
                if(it.isNotEmpty()) {
                    logger.d(it)
                }
                if(pb.current < 100) {
                    pb.step()
                }
            }


            pb.extraMessage = "Finished"
            sleep(100)
        }
    }

    companion object {
        const val OLD_PACKAGE = "io.bloco.template"
        const val OLD_APPNAME = "Base Template"
        const val OLD_TITLE = "template"
    }
}

fun main(args: Array<String>) = NewProjectCI().main(args)