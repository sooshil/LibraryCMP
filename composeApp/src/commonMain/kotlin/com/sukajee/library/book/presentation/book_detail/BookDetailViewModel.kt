package com.sukajee.library.book.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sukajee.library.app.Routes
import com.sukajee.library.book.domain.BookRepository
import com.sukajee.library.core.domain.onError
import com.sukajee.library.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val bookId = savedStateHandle.toRoute<Routes.BookDetail>().id
    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart {
            getBookDescription()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    fun onAction(action: BookDetailAction) {
        when (action) {
            BookDetailAction.OnBackClick -> {

            }

            BookDetailAction.OnFavoriteClick -> {

            }

            is BookDetailAction.OnSelectedBookChange -> {
                _state.update { currentState ->
                    currentState.copy(
                        book = action.book
                    )
                }
            }
        }
    }

    fun getBookDescription() {
        viewModelScope.launch {
            repository
                .getBookDescription(bookId = bookId)
                .onSuccess { description ->
                    _state.update { currentState ->
                        currentState.copy(
                            book = currentState.book?.copy(
                                description = description
                            ),
                            isLoading = false
                        )
                    }
                }
                .onError { error ->

                }
        }
    }
}