plugins {
    `kotlin-dsl`
}

group = "io.bloco.template.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinFeature") {
            id = "template.kotlin.feature"
            implementationClass = "KotlinFeatureConventionPlugin"
        }
    }
}
