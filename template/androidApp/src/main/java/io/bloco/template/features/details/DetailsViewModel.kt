package io.bloco.template.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Provides
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
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(bookId: String): DetailsViewModel
    }

    private val _bookDetailsUpdateState =
        MutableStateFlow<APIRequestState>(APIRequestState.LoadingFromAPI)
    val bookDetailsUpdateState = _bookDetailsUpdateState.asStateFlow()

    init {
        viewModelScope.launch {
            getBook(bookId)
                .onSuccess { _bookDetailsUpdateState.value = APIRequestState.Success(it) }
                .onFailure { _bookDetailsUpdateState.value = APIRequestState.ErrorFromAPI }
        }
    }

    sealed class APIRequestState {
        object LoadingFromAPI : APIRequestState()
        data class Success(val book: BookDetails) : APIRequestState()
        object ErrorFromAPI : APIRequestState()
    }

}