package io.bloco.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailsDto(
    @SerialName("title")
    val title: String
)