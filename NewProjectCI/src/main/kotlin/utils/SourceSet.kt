package utils

enum class SourceSet(val path: String) {
    Main("app/src/main/java"),
    AndroidTest("app/src/androidTest/java"),
    Test("app/src/test/java")
}