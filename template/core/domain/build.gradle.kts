plugins {
    id("template.kotlin.feature")
    id("template.coroutines")
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":core:data"))

    implementation(libs.bundles.javax)
}
