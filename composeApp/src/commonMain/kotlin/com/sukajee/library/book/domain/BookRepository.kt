package com.sukajee.library.book.domain

import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavouriteBooks(): Flow<List<Book>>
    fun isBookFavourite(bookId: String): Flow<Boolean>
    suspend fun markBookAsFavourite(book: Book): Result<Unit, DataError.Local>
    suspend fun deleteBookFromFavourite(bookId: String)
}