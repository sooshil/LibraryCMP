package com.sukajee.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sukajee.library.book.presentation.components.BookSearchBar

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            BookSearchBar(
                searchQuery = "",
                onSearchQueryChange = {},
                onImeSearch = {}
            )
        }
    }
}