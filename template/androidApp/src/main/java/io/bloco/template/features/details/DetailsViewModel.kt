package io.bloco.template.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.domain.GetBook
import io.bloco.core.domain.models.BookDetails
import io.bloco.template.shared.PublishFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    getBook: GetBook
) : ViewModel() {
    private val events = PublishFlow<Event>()

    private val _bookDetails = MutableStateFlow(BookDetails(""))
    val bookDetails = _bookDetails.asStateFlow()

    private val _bookDetailsUpdateState =
        MutableStateFlow<APIRequestState>(APIRequestState.LoadingFromAPI)
    val bookDetailsUpdateState = _bookDetailsUpdateState.asStateFlow()

    init {
        events.filterIsInstance<Event.IdReceived>().onEach { event ->
            _bookDetailsUpdateState.value = APIRequestState.LoadingFromAPI
            getBook(event.id).onSuccess {
                _bookDetails.emit(it)
                _bookDetailsUpdateState.value = APIRequestState.Success
            }.onFailure {
                _bookDetailsUpdateState.value = APIRequestState.ErrorFromAPI
            }
        }.launchIn(viewModelScope)
    }

    fun sendBookId(id: String) {
        events.tryEmit(Event.IdReceived(id = id))
    }

    private sealed class Event {
        data class IdReceived(val id: String) : Event()
    }

    sealed class APIRequestState {
        object LoadingFromAPI : APIRequestState()
        object Success : APIRequestState()
        object ErrorFromAPI : APIRequestState()
    }
}