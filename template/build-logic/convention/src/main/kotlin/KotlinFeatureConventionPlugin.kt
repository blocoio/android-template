import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("kotlin")
            }
        }
    }
}

