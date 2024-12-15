@file:OptIn(FlowPreview::class)

package com.sukajee.library.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukajee.library.book.domain.Book
import com.sukajee.library.book.domain.BookRepository
import com.sukajee.library.core.domain.map
import com.sukajee.library.core.domain.onError
import com.sukajee.library.core.domain.onSuccess
import com.sukajee.library.core.presentation.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random.Default.nextInt

class BookListViewModel(
    private val repository: BookRepository
) : ViewModel() {

    private var cashedBooks = emptyList<Book>()
    private var searchJob: Job? = null
    private var observeFavouriteBooksJob: Job? = null

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cashedBooks.isEmpty()) {
                observeSearchQuery()
            }
            observeFavouriteBooks()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { currentState ->
                            currentState.copy(
                                searchResults = cashedBooks
                            )
                        }
                    }

                    query.length >= 3 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeFavouriteBooks() {
        observeFavouriteBooksJob?.cancel()
        observeFavouriteBooksJob = repository.getFavouriteBooks()
            .onEach { favouriteBooks ->
                _state.update { currentState ->
                    currentState.copy(
                        favouriteBooks = favouriteBooks
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
        repository.searchBooks(query)
            .onSuccess { books ->
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        searchResults = books,
                        errorMessage = null
                    )
                }
            }
            .onError { remoteError ->
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        searchResults = emptyList(),
                        errorMessage = remoteError.toUiText()
                    )
                }
            }
    }

    fun onAction(action: BookListAction) {
        when (action) {
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