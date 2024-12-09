package com.sukajee.library.book.presentation.book_list

import com.sukajee.library.book.domain.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnTabSelected(val index: Int): BookListAction
    data class OnBookClick(val book: Book): BookListAction
}