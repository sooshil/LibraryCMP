package com.sukajee.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sukajee.library.book.domain.Book
import com.sukajee.library.book.presentation.book_list.BookListScreen
import com.sukajee.library.book.presentation.book_list.BookListState
import com.sukajee.library.book.presentation.book_list.components.BookSearchBar

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            BookSearchBar(
                searchQuery = "",
                onSearchQueryChange = {},
                onImeSearch = {}
            )
        }
    }
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Sample Book - $it",
        imageUrl = "sample url",
        authors = listOf("Sample Author"),
        description = "Sample Description",
        languages = listOf("English"),
        firstPublishedYear = "1988",
        averageRating = 4.33299,
        ratingsCount = 445,
        numPages = 83,
        numEditions = 4
    )
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    MaterialTheme {
        BookListScreen(
            state = BookListState(
                searchQuery = "",
                searchResults = books
            ),
            onAction = {

            }
        )
    }

}