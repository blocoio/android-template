package io.bloco.template.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.commons.PublishFlow
import io.bloco.core.domain.GetBooks
import io.bloco.core.domain.models.Book
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.ErrorFromAPI
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.LoadingFromAPI
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.UpdateSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getBooks: GetBooks
) : ViewModel() {

    private val events = PublishFlow<Event>()

    private val _state = MutableStateFlow<ListScreenUiState>(LoadingFromAPI)
    val state = _state.asStateFlow()

    init {
        events
            .filterIsInstance<Event.Refresh>()
            .onStart { updateBookList() }
            .onEach {
                _state.value = LoadingFromAPI
                updateBookList()
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        viewModelScope.launch { events.emit(Event.Refresh) }
    }

    private suspend fun updateBookList() {
        getBooks()
            .onSuccess { _state.value = UpdateSuccess(it) }
            .onFailure { _state.value = ErrorFromAPI }
    }

    private sealed class Event {
        object Refresh : Event()
    }

    sealed interface ListScreenUiState {
        object LoadingFromAPI : ListScreenUiState
        data class UpdateSuccess(val books: List<Book>) : ListScreenUiState
        object ErrorFromAPI : ListScreenUiState
    }
}
