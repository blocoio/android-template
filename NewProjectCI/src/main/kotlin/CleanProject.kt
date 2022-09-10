import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.apache.commons.io.FileUtils
import java.io.File

fun NewProjectCI.cleanProject(): Pair<Int, Flow<String>> {
    return Pair(5, flow {
        // get rid of idea settings
        FileUtils.deleteDirectory(File("./$title/.idea"))
        emit("Cleared .idea")
        // get rid of gradle cache
        FileUtils.deleteDirectory(File("./$title/.gradle"))
        emit("Cleared .gradle")
        // get rid of the git history
        FileUtils.deleteDirectory(File("./$title/.git"))
        emit("Cleared .git")
        // get rid of the build
        FileUtils.deleteDirectory(File("./$title/.build"))
        emit("Cleared .build")
        FileUtils.deleteDirectory(File("./$title/app/build"))
        emit("Cleared app/build")
    }
    )
}