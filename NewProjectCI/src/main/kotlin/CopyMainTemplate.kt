import NewProjectCI.Companion.OLD_TITLE
import com.github.ajalt.clikt.core.UsageError
import kotlinx.coroutines.flow.MutableSharedFlow
import utils.copyDirectory
import java.io.File

fun NewProjectCI.copyMainTemplate(): Pair<Int, MutableSharedFlow<String>> = try {
    val templatePath = File("../$OLD_TITLE")
    val newPath = File("../$title")

    templatePath.copyDirectory(newPath)
} catch (e: Throwable) {
    throw UsageError("Template cant be found in this directory")
}
