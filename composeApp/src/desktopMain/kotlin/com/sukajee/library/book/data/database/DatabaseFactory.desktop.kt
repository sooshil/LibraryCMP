package com.sukajee.library.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavouriteBooksDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "BookLibrary")
            os.contains("mac") -> File(userHome, "Library/Application Support/BookLibrary")
            else -> File(userHome, ".local/share/BookLibrary")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavouriteBooksDatabase.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}