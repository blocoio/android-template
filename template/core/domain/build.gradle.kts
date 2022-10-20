plugins {
    id("template.kotlin.feature")
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":core:data"))

    implementation(libs.javax.inject)
}