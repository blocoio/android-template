import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("Main.kt")
}

repositories {
    mavenCentral()
}
// https://stackoverflow.com/questions/26469365/building-a-self-executable-jar-with-gradle-and-kotlin
dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.5.0")
    implementation("me.tongfei:progressbar:0.9.3")
    implementation("org.lucee:commons-io:2.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}