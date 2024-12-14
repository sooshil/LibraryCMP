package com.sukajee.library.book.data.mappers

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
