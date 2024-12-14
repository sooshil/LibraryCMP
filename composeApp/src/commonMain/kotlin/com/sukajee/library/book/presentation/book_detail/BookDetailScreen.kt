package com.sukajee.library.book.presentation.book_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sukajee.library.book.presentation.book_detail.components.BlurredImageBackground

@Composable
fun BookDetailScreenRoot(
    navController: NavController,
    viewModel: BookDetailViewModel,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    BookDetailScreen(
        state = state.value,
        onAction = { action ->
            when(action) {
                is BookDetailAction.OnBackClick -> {
                    navController.navigateUp()
                }
                else -> Unit
            }
            viewModel::onAction
        }
    )
}

@Composable
fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    BlurredImageBackground(
        imageUrl = state.book?.imageUrl ?: "",
        isFavorite = state.isFavourite,
        onFavouriteClick = {
            onAction(BookDetailAction.OnFavoriteClick)
        },
        onBackClick = {
            onAction(BookDetailAction.OnBackClick)
        },
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Book Detail")
        }
    }
}