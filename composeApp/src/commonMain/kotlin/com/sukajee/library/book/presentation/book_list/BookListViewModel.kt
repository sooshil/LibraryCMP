package com.sukajee.library.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookListViewModel: ViewModel() {

    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when(action) {
            is BookListAction.OnBookClick -> {

            }
            is BookListAction.OnSearchQueryChange -> {
                _state.update { currentState ->
                    currentState.copy(
                        searchQuery = action.query
                    )
                }
            }
            is BookListAction.OnTabSelected -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedTabIndex = action.index
                    )
                }
            }
        }
    }
}