package io.bloco.data_test

object DataTestResources {
    fun bookListJson(success: Boolean = true): String =
        if (success) {
            javaClass.classLoader!!
                .getResource("bookList-Success.json")!!
                .readText()
        } else {
            javaClass.classLoader!!
                .getResource("bookList-Error.json")!!
                .readText()
        }
}
