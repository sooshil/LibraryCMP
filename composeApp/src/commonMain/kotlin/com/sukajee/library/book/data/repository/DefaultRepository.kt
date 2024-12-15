package com.sukajee.library.book.data.repository

import androidx.sqlite.SQLiteException
import com.sukajee.library.book.data.database.FavouriteBooksDao
import com.sukajee.library.book.data.mappers.toBook
import com.sukajee.library.book.data.mappers.toBookEntity
import com.sukajee.library.book.data.network.RemoteBookDataSource
import com.sukajee.library.book.domain.Book
import com.sukajee.library.book.domain.BookRepository
import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result
import com.sukajee.library.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val dao: FavouriteBooksDao
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
        val localResult = dao.getFavoriteBookById(bookId)
        return if (localResult == null) {
            remoteBookDataSource.getBookDetails(
                bookWorkId = bookId
            ).map { bookWorkDto ->
                bookWorkDto.description
            }
        } else Result.Success(localResult.description)
    }

    override fun getFavouriteBooks(): Flow<List<Book>> {
        return dao.getFavoriteBooks()
            .map { bookEntityList ->
                bookEntityList.map { bookEntity ->
                    bookEntity.toBook()
                }
            }
    }

    override fun isBookFavourite(bookId: String): Flow<Boolean> {
        return dao.getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { bookEntity ->
                    bookEntity.id == bookId
                }
            }
    }

    override suspend fun markBookAsFavourite(book: Book): Result<Unit, DataError.Local> {
        return try {
            dao.upsertBook(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteBookFromFavourite(bookId: String) {
        dao.deleteFavoriteBook(bookId)
    }
}