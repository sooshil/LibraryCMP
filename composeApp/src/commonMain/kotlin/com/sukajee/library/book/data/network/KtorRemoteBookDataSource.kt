package com.sukajee.library.book.data.network

import com.sukajee.library.book.data.dto.SearchedResponseDto
import com.sukajee.library.core.data.safeApiCall
import com.sukajee.library.core.domain.DataError
import com.sukajee.library.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient,
): RemoteBookDataSource {

   override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchedResponseDto, DataError.Remote> {
        return safeApiCall {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                // This will return only the specified fields, like graphql.
                parameter("fields", "key,title,language,author_key,author_name,cover_edition_key,cover_i,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count")
            }
        }
    }
}