package io.bloco.core.domain.models

import io.bloco.core.data.models.BookDto

private const val KEY_PREFIX = "/works/"

data class Book(
    val key: String,
    val title: String
)

fun BookDto.toModel(): Book = Book(
    key = key.removePrefix(KEY_PREFIX),
    title = title
)
