package utils

enum class SourceSet(var path: String) {
    Main("androidApp/src/main/java"),
    AndroidTest("androidApp/src/androidTest/java"),
    Test("androidApp/src/test/java"),
}