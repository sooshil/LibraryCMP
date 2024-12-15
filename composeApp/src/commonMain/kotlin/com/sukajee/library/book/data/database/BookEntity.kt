package com.sukajee.library.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String?,
    val imageUrl: String?,
    val authors: List<String>?,
    val description: String?,
    val languages: List<String>?,
    val firstPublishedYear: String?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val numPages: Int?,
    val numEditions: Int?,
)
