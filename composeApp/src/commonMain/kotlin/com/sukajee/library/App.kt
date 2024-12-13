package com.sukajee.library

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sukajee.library.book.presentation.book_list.BookListScreenRoot
import com.sukajee.library.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<BookListViewModel>()
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = {}
        )
    }
}