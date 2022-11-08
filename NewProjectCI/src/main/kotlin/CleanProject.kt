import NewProjectCI.Companion.OLD_TITLE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.apache.commons.io.FileUtils
import java.io.File

fun NewProjectCI.cleanProject(): Pair<Int, Flow<String>> {
    return Pair(6, flow {
        // get rid of idea settings
        FileUtils.deleteDirectory(File("../$OLD_TITLE/.idea"))
        emit("Cleared .idea")
        // get rid of gradle cache
        FileUtils.deleteDirectory(File("../$OLD_TITLE/.gradle"))
        emit("Cleared .gradle")
        // get rid of the git history
        FileUtils.deleteDirectory(File("../$OLD_TITLE/.git"))
        emit("Cleared .git")
        // get rid of the build
        FileUtils.deleteDirectory(File("../$OLD_TITLE/.build"))
        emit("Cleared .build")
        FileUtils.deleteDirectory(File("../$OLD_TITLE/app/build"))
        emit("Cleared app/build")
    }
    )
}