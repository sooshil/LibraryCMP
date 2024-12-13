package com.sukajee.library.book.domain

import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote>
}