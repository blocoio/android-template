@file:Suppress("UnstableApiUsage")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.reporter.result.Result
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        @Suppress("GradleDynamicVersion")
        classpath("com.github.ben-manes:gradle-versions-plugin:+")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.0.0")
    }
}

// Don't use plugin {} for this one, check https://github.com/ben-manes/gradle-versions-plugin
apply(plugin = libs.plugins.versions.get().pluginId)
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}

val detektVersion = libs.versions.detekt.get().toString()
val ktlintAlias: String = libs.plugins.ktlint.get().pluginId
val detektAlias: String = libs.plugins.detekt.get().pluginId

allprojects {
    pluginManager.apply {
        apply(ktlintAlias)
        apply(detektAlias)
    }

    extensions.configure<DetektExtension> {
        autoCorrect = true
        config.setFrom(files("${rootProject.projectDir}/configs/detekt.yml"))
    }

    extensions.configure<KtlintExtension> {
        filter {
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
        }
    }

    dependencies {
        add("detektPlugins", "io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all {
                if (candidate.version.isNonStable() && !currentVersion.isNonStable()) {
                    reject("Release candidate")
                }
            }
        }
    }

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = closureOf<Result> {
        val updatable = outdated.dependencies
        if (updatable.isNotEmpty()) {
            val writer = java.io.StringWriter()
            val html = groovy.xml.MarkupBuilder(writer)

            html.withGroovyBuilder {
                "html"() {
                    "body"() {
                        "table"() {
                            "thead"() {
                                "tr"() {
                                    "td"("Package")
                                    "td"("Current version")
                                    "td"("Latest version")
                                }
                            }
                            "tbody"() {
                                updatable.forEach { dependency ->
                                    "tr"() {
                                        "td"("${dependency.group}:${dependency.name}")
                                        "td"(dependency.version)
                                        "td"(
                                            dependency.available.release
                                                ?: dependency.available.milestone
                                        )
                                    }
                                }
                            }
                        }

                        if (gradle.current.isUpdateAvailable) {
                            "p"("Theres currently a gradle update available, your current version: ${gradle.current.version}")
                        }
                    }
                }
            }
            println(writer.toString())
        }
    }
    outputDir = "build/dependencyUpdates"
    reportfileName = "report.html"
}
