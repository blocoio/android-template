plugins {
    `kotlin-dsl`
}

group = "io.bloco.template.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
}

gradlePlugin {
    plugins {
        register("coroutines") {
            id = "template.coroutines"
            implementationClass = "CoroutinesConventionPlugin"
        }

        register("kotlinFeature") {
            id = "template.kotlin.feature"
            implementationClass = "KotlinFeatureConventionPlugin"
        }
    }
}
