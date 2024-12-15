package com.sukajee.library.book.data.mappers

import com.sukajee.library.book.data.database.BookEntity
import com.sukajee.library.book.data.dto.SearchedBookDto
import com.sukajee.library.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    val imageUrlPart = if(coverKey != null) "olid/${coverKey}-L.jpg" else "id/${coverAlternativeKey}-L.jpg"
    return Book(
        id = id.substringAfterLast('/'),
        title = title,
        imageUrl = "https://covers.openlibrary.org/b/$imageUrlPart",
        authors = authorNames,
        description = null,
        languages = languages,
        firstPublishedYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingsCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions,
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id.toString(),
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        description = description,
        languages = languages,
        firstPublishedYear = firstPublishedYear,
        averageRating = averageRating,
        ratingsCount = ratingsCount,
        numPages = numPages,
        numEditions = numEditions
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        description = description,
        languages = languages,
        firstPublishedYear = firstPublishedYear,
        averageRating = averageRating,
        ratingsCount = ratingsCount,
        numPages = numPages,
        numEditions = numEditions
    )
}
