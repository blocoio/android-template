plugins {
    id("template.kotlin.feature")
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

dependencies {
    implementation(project(":core:commons"))

    api(libs.bundles.network)
    implementation(libs.javax.inject)
}