package io.bloco.template.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.commons.PublishFlow
import io.bloco.core.domain.GetBooks
import io.bloco.core.domain.models.Book
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

    private val _bookListUpdateState =
        MutableStateFlow<BookListUpdateState>(BookListUpdateState.LoadingFromAPI)
    val bookListUpdateState = _bookListUpdateState.asStateFlow()

    init {
        events
            .filterIsInstance<Event.Refresh>()
            .onStart { updateBookList(getBooks) }
            .onEach { updateBookList(getBooks) }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        events.tryEmit(Event.Refresh)
    }

    private suspend fun updateBookList(getBooks: GetBooks) {
        getBooks()
            .onSuccess { _bookListUpdateState.value = BookListUpdateState.UpdateSuccess(it) }
            .onFailure { _bookListUpdateState.value = BookListUpdateState.ErrorFromAPI }
    }

    private sealed class Event {
        object Refresh : Event()
    }

    sealed class BookListUpdateState {
        object LoadingFromAPI : BookListUpdateState()
        data class UpdateSuccess(val books: List<Book>) : BookListUpdateState()
        object ErrorFromAPI : BookListUpdateState()
    }
}