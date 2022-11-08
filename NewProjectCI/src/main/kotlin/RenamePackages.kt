import NewProjectCI.Companion.OLD_PACKAGE
import kotlinx.coroutines.flow.flow
import org.apache.commons.io.FileUtils
import utils.SourceSet
import java.io.File

fun NewProjectCI.renamePackages() =  Pair( SourceSet.values().size, flow {
    // Rename Package folders
    SourceSet.values().forEach { sourceSet ->
        emit("Renaming packages at $title/${sourceSet.path}")
        renameTemplateFolderStructure("../$title", sourceSet, packageName)
    }
})

private fun renameTemplateFolderStructure(initialPath: String, sourceSet: SourceSet, packageName: String) {
    val oldPackageFolders = OLD_PACKAGE.split(".")
    val oldPackageUriFormat = OLD_PACKAGE.replace(".", "/")

    // Move important files to temp folder at root
    val tempFolder =  File("$initialPath/${sourceSet.path}/temp")
    FileUtils.moveDirectory(
        File("$initialPath/${sourceSet.path}/$oldPackageUriFormat"),
        tempFolder
    )

    // Delete old packages
    FileUtils.deleteDirectory(File("$initialPath/${sourceSet.path}/${oldPackageFolders.first()}"))

    // create and move the files to the new packages
    val newPackageUri = packageName.replace(".", "/")
    FileUtils.moveDirectory(
        tempFolder,
        File("$initialPath/${sourceSet.path}/$newPackageUri")
    )
}