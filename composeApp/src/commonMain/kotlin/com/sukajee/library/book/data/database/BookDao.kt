package com.sukajee.library.book.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteBooksDao {
    @Upsert
    suspend fun upsertBook(book: BookEntity)

    @Query("SELECT * FROM book")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getFavoriteBookById(id: String): BookEntity?

    @Query("DELETE FROM book WHERE id = :bookId")
    suspend fun deleteFavoriteBook(bookId: String)
}