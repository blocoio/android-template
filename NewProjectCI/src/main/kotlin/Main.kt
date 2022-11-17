import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import me.tongfei.progressbar.ProgressBar
import utils.Logger
import java.lang.Thread.sleep

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

    override fun run() = runBlocking(Dispatchers.IO) {
        Logger.enabled = verbose

        val numberOrSteps = 5
        val percentagePerStep = 100 / numberOrSteps

        ProgressBar("New Project", 100).use { pb ->
            pb.extraMessage = "Cleaning folders"
            sleep(DELAY_BETWEEN_STEPS_IN_MILLIS)
            val (numberOfOperations, flowFolders) = cleanProject()
            var stepBy: Long = (percentagePerStep/numberOfOperations).toLong()
            flowFolders.collect {
                Logger.d(it)
                pb.stepBy(stepBy)
            }


            pb.extraMessage = "Coping template"
            sleep(DELAY_BETWEEN_STEPS_IN_MILLIS)
            val (numberOfFilesToCopy, stateOfCopiedFiles) = copyMainTemplate()
            stepBy = (numberOfFilesToCopy/percentagePerStep).toLong()
            var step = 0
            var i = 0
            stateOfCopiedFiles.take(numberOfFilesToCopy).collect {
                Logger.d("[${++i}/$numberOfFilesToCopy] $it")
                step++
                if (step >= stepBy) {
                    pb.step()
                    step = 0
                }
            }

            pb.extraMessage = "Renaming Packages"
            sleep(DELAY_BETWEEN_STEPS_IN_MILLIS)
            val (numberOfSourceSets, flowRenaming) = renamePackages()
            stepBy = (percentagePerStep/numberOfSourceSets).toLong()
            flowRenaming.collect {
                Logger.d(it)
                pb.stepBy(stepBy)
            }

            pb.extraMessage = "Replacing data in files"
            sleep(DELAY_BETWEEN_STEPS_IN_MILLIS)
            replaceInFiles().collect {
                if(it.isNotEmpty()) {
                    Logger.d(it)
                }
                if(pb.current < 100) {
                    pb.step()
                }
            }


            pb.extraMessage = "Finished"
            sleep(DELAY_BETWEEN_STEPS_IN_MILLIS)
        }
    }

    companion object {
        // so the progress bar has time to sync up when the step is too fast
        const val DELAY_BETWEEN_STEPS_IN_MILLIS = 175L
        const val OLD_PACKAGE = "io.bloco.template"
        const val OLD_APPNAME = "Base Template"
        const val OLD_TITLE = "template"
    }
}

fun main(args: Array<String>) = NewProjectCI().main(args)