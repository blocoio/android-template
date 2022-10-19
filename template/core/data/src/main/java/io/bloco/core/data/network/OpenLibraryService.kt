package io.bloco.core.data.network

import io.bloco.core.data.models.BookDetailsDto
import io.bloco.core.data.models.BookRecords
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class OpenLibraryService
@Inject constructor(
    private val httpClient: OpenLibraryHttpClient
) {

    suspend fun getBooks(): Result<BookRecords> = try {
        Result.success(
            httpClient().get {
                url(path = "/search.json?q=android&limit=10")
            }.body()
        )
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getBook(id: String): Result<BookDetailsDto> = try {
        Result.success(
            httpClient().get {
                url(path = "/works/$id.json")
            }.body()
        )
    } catch (e: Exception) {
        Result.failure(e)
    }
}