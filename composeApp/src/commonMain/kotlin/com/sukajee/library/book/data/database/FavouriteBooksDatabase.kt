package com.sukajee.library.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookEntity::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavouriteBooksDatabase: RoomDatabase() {
    abstract val dao: FavouriteBooksDao

    companion object {
        const val DATABASE_NAME = "favourite_books_db"
    }
}