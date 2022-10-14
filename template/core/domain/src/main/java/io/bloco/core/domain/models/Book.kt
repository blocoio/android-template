package io.bloco.core.domain.models

import io.bloco.core.data.models.BookDto

data class Book(
    val key: String,
    val title: String
)

fun BookDto.toModel(): Book = Book(
    key = key.removePrefix(Key_Prefix),
    title = title
)

private const val Key_Prefix = "/works/"