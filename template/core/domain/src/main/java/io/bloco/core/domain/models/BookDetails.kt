package io.bloco.core.domain.models

import io.bloco.core.data.models.BookDetailsDto

data class BookDetails(
    val title: String
    )

fun BookDetailsDto.toModel(): BookDetails = BookDetails(
    title = title
)