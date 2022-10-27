pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "template"
include(
    ":androidApp",
    ":lint-checks",
    ":core:commons",
    ":core:domain",
    ":core:data",
)

// testing helper submodules
include(
    ":core:data-test",
)
