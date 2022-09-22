package io.bloco.core.data.network

import io.bloco.core.data.models.Book
import io.bloco.core.data.models.BookRecords
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class OpenLibraryService
@Inject constructor(
    @OpenLibraryHttpClient private var httpClient: HttpClient
) {
    suspend fun getBooks(): Result<BookRecords> = try {
        Result.success(
            httpClient.get {
                url(path = "/")
            }.body()
        )
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getBook(id: String): Result<Book> = try {
        Result.success(
            httpClient.get {
                url(path = "/")
            }.body()
        )
    } catch (e: Exception) {
        Result.failure(e)
    }
}