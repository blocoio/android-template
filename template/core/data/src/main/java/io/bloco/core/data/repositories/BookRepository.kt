package io.bloco.core.data.repositories

import io.bloco.core.data.models.BookDetailsDto
import io.bloco.core.data.models.BookDto
import io.bloco.core.data.network.OpenLibraryService
import javax.inject.Inject

class BookRepository
@Inject constructor(
    private val openLibraryService: OpenLibraryService
) {
    suspend fun getBooks(): Result<List<BookDto>> =
        openLibraryService.getBooks().map {
            it.docs
        }

    suspend fun getBookDetails(id: String): Result<BookDetailsDto> =
        openLibraryService.getBook(id)
}