plugins {
    id("template.kotlin.feature")
}

dependencies {
    implementation(libs.bundles.network)
    implementation(libs.javax.inject)
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}