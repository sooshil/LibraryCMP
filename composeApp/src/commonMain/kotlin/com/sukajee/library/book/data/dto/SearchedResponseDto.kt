package com.sukajee.library.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedResponseDto(
    @SerialName("docs")
    val results: List<SearchedBookDto>,
)
