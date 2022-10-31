package io.bloco.template.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.bloco.core.domain.GetBook
import io.bloco.core.domain.models.BookDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    @Assisted bookId: String,
    getBook: GetBook,
) : ViewModel() {

    private val _updateState =
        MutableStateFlow<UiState>(UiState.LoadingFromAPI)
    val updateState = _updateState.asStateFlow()

    init {
        viewModelScope.launch {
            getBook(bookId)
                .onSuccess { _updateState.value = UiState.Success(it) }
                .onFailure { _updateState.value = UiState.ErrorFromAPI }
        }
    }

    sealed interface UiState {
        object LoadingFromAPI : UiState
        data class Success(val book: BookDetails) : UiState
        object ErrorFromAPI : UiState
    }

    @AssistedFactory
    interface Factory {
        fun create(bookId: String): DetailsViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            factory: Factory,
            bookId: String,
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(bookId) as T
            }
        }
    }
}
