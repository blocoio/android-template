package io.bloco.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    @SerialName("key")
    val key: String,
    @SerialName("title")
    val title: String
)
