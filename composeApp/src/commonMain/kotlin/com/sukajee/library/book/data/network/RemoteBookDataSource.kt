package com.sukajee.library.book.data.network

import com.sukajee.library.book.data.dto.BookWorkDto
import com.sukajee.library.book.data.dto.SearchedResponseDto
import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchedResponseDto, DataError.Remote>

    suspend fun getBookDetails(
        bookWorkId: String
    ): Result<BookWorkDto, DataError.Remote>

}