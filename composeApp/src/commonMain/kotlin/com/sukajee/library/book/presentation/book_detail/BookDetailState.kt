package com.sukajee.library.book.presentation.book_detail

import com.sukajee.library.book.domain.Book
import com.sukajee.library.core.presentation.UiText

data class BookDetailState(
    val isLoading: Boolean = true,
    val book: Book? = null,
    val isFavourite: Boolean = false,
    val errorMessage: UiText? = null
)
