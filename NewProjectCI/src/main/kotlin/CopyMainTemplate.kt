import NewProjectCI.Companion.OLD_TITLE
import com.github.ajalt.clikt.core.UsageError
import utils.copyDirectory
import java.io.File

fun NewProjectCI.copyMainTemplate() = try {
    val templatePath = File("./$OLD_TITLE")
    val newPath = File("./$title")

    templatePath.copyDirectory(newPath)
} catch (e: Throwable) {
    throw UsageError("Template cant be found in this directory")
}
