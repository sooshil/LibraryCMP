package com.sukajee.library.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavouriteBooksDatabase> {
    override fun initialize(): FavouriteBooksDatabase
}