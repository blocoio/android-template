package io.bloco.core.domain

import io.bloco.core.data.repositories.BookRepository
import io.bloco.core.domain.models.BookDetails
import io.bloco.core.domain.models.toModel
import javax.inject.Inject

class GetBook @Inject constructor(
    private val bookRepository: BookRepository

) {
    suspend operator fun invoke(id: String): Result<BookDetails> {
        return bookRepository.getBookDetails(id).map { bookDetails ->
            println("Title: " + bookDetails.title)
            bookDetails.toModel()
        }
    }
}
