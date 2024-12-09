package com.sukajee.library

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sukajee.library.book.presentation.book_list.BookListScreenRoot
import com.sukajee.library.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember {
                BookListViewModel()
            },
            onBookClick = {}
        )
    }
}