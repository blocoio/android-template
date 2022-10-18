plugins {
    id("template.kotlin.feature")
    id(libs.plugins.kotlin.serialization.get().pluginId)
}

dependencies {
    api(libs.bundles.network)
    implementation(libs.javax.inject)
}