package com.sukajee.library

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sukajee.library.book.data.network.KtorRemoteBookDataSource
import com.sukajee.library.book.data.repository.DefaultRepository
import com.sukajee.library.book.presentation.book_list.BookListScreenRoot
import com.sukajee.library.book.presentation.book_list.BookListViewModel
import com.sukajee.library.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(httpEngine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember {
                BookListViewModel(
                    repository = DefaultRepository(
                        remoteBookDataSource = KtorRemoteBookDataSource(
                            httpClient = HttpClientFactory.create(
                                engine = httpEngine
                            )
                        )
                    )
                )
            },
            onBookClick = {}
        )
    }
}