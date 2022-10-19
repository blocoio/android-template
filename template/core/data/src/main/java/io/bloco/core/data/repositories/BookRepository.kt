package io.bloco.core.data.repositories

import io.bloco.core.data.models.BookDetailsDto
import io.bloco.core.data.models.BookDto
import io.bloco.core.data.models.BookRecords
import io.bloco.core.data.network.OpenLibraryService
import javax.inject.Inject

class BookRepository
@Inject constructor(
    private val openLibraryService: OpenLibraryService
) {
    suspend fun getBooks(): Result<BookRecords> =
        openLibraryService.getBooks()

    suspend fun getBookDetails(id: String): Result<BookDetailsDto> =
        openLibraryService.getBook(id)
}