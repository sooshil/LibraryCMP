package com.sukajee.library.book.data.repository

import com.sukajee.library.book.data.mappers.toBook
import com.sukajee.library.book.data.network.RemoteBookDataSource
import com.sukajee.library.book.domain.Book
import com.sukajee.library.book.domain.BookRepository
import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result
import com.sukajee.library.core.domain.map

class DefaultRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(
            query = query
        ).map { bookDto ->
            bookDto.results.map {
                it.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteBookDataSource.getBookDetails(
            bookWorkId = bookId
        ).map { bookWorkDto ->
            bookWorkDto.description
        }
    }
}