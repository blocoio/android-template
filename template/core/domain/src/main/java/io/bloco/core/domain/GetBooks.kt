package io.bloco.core.domain

import io.bloco.core.data.repositories.BookRepository
import io.bloco.core.domain.models.Book
import io.bloco.core.domain.models.toModel
import javax.inject.Inject

class GetBooks @Inject constructor(
    private val bookRepository: BookRepository

) {
    suspend operator fun invoke(): Result<List<Book>> {
        return bookRepository.getBooks()
            .map { it.docs }
            .map { bookList -> bookList.map { it.toModel() }
        }
    }
}