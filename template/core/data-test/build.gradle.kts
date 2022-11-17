plugins {
    id("template.kotlin.feature")
    id("template.coroutines")
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":core:data"))

    implementation(libs.ktor.engine.mock)
}
