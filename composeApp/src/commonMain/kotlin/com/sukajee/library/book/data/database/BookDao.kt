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

    @Query("SELECT * FROM BookEntity")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    suspend fun getFavoriteBookById(id: String): BookEntity?

    @Delete("DELETE FROM BookEntity WHERE id = :id")
    suspend fun deleteFavoriteBook(book: BookEntity)
}