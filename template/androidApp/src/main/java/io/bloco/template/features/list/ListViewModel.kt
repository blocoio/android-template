package io.bloco.template.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.domain.GetBooks
import io.bloco.core.domain.models.Book
import io.bloco.template.shared.PublishFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getBooks: GetBooks
) : ViewModel() {

    private val events = PublishFlow<Event>()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books = _books.asStateFlow()

    private val _bookListUpdateState =
        MutableStateFlow<BookListUpdateState>(BookListUpdateState.LoadingFromAPI)
    val bookListUpdateState = _bookListUpdateState.asStateFlow()

    init {
        events
            .filterIsInstance<Event.Refresh>()
            .onStart {
                getBooks()
                    .onSuccess {
                        _bookListUpdateState.value = BookListUpdateState.UpdateSuccess
                        _books.value = it
                    }
                    .onFailure {
                        _bookListUpdateState.value = BookListUpdateState.ErrorFromAPI
                    }
            }
            .onEach {
                //TODO: Isto não devia estar duplicado
                getBooks()
                    .onSuccess {
                        _bookListUpdateState.value = BookListUpdateState.UpdateSuccess
                        _books.value = it
                    }
                    .onFailure {
                        _bookListUpdateState.value = BookListUpdateState.ErrorFromAPI
                    }
            }.launchIn(viewModelScope)
    }

    fun refresh() {
        events.tryEmit(Event.Refresh)
    }

    private sealed class Event {
        object Refresh : Event()
    }

    sealed class BookListUpdateState {
        object LoadingFromAPI : BookListUpdateState()
        object UpdateSuccess : BookListUpdateState()
        object ErrorFromAPI : BookListUpdateState()
    }
}