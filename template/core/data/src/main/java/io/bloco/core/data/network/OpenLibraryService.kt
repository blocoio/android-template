package io.bloco.core.data.network

import io.bloco.core.commons.BackgroundDispatcher
import io.bloco.core.commons.endpoints.OpenLibraryEndpoint
import io.bloco.core.commons.loge
import io.bloco.core.data.models.BookDetailsDto
import io.bloco.core.data.models.BookRecords
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OpenLibraryService
@Inject constructor(
    private val httpClient: OpenLibraryHttpClient,
    @BackgroundDispatcher private val coroutineContext: CoroutineContext
) {

    suspend fun getBooks(keyword: String): Result<BookRecords> = withContext(coroutineContext) {
        return@withContext try {
            Result.success(
                httpClient().get {
                    url(path = OpenLibraryEndpoint.search(keyword))
                }.body()
            )
        } catch (e: Exception) {
            loge("Failed to get Books", e)
            Result.failure(e)
        }
    }

    suspend fun getBook(id: String): Result<BookDetailsDto> = withContext(coroutineContext) {
        return@withContext try {
            Result.success(
                httpClient().get {
                    url(path = OpenLibraryEndpoint.work(id))
                }.body()
            )
        } catch (e: Exception) {
            loge("Failed to get Book", e)
            Result.failure(e)
        }
    }
}
