package io.bloco.datatest

object DataTestResources {
    fun bookListJson(): String =
            loadJsonResource("bookList")

    private fun loadJsonResource(fileName: String) =
        javaClass.classLoader!!
            .getResource("$fileName.json")!!
            .readText()
}
