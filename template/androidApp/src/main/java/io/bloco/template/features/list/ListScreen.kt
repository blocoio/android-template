package io.bloco.template.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.bloco.core.domain.models.Book
import io.bloco.template.R
import io.bloco.template.component.Toast
import io.bloco.template.features.list.ListViewModel.ListScreenUiState
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.ErrorFromAPI
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.LoadingFromAPI
import io.bloco.template.features.list.ListViewModel.ListScreenUiState.UpdateSuccess
import io.bloco.template.theme.TemplateTheme
import io.bloco.template.utils.preview.DeviceFormatPreview
import io.bloco.template.utils.preview.FontScalePreview
import io.bloco.template.utils.preview.ThemeModePreview

@Composable
fun ListScreen(
    listViewModel: ListViewModel,
    openDetailsClicked: (String) -> Unit,
) {
    val bookListUpdateState by listViewModel.state.collectAsState()

    when (val state = bookListUpdateState) {
        ErrorFromAPI -> ErrorFromApi()
        is UpdateSuccess, LoadingFromAPI -> ListBooks(
            state = state,
            onRefresh = listViewModel::refresh,
            onDetailsClicked = openDetailsClicked,
        )
    }
}

@Composable
@Suppress("DEPRECATION") // SwipeRefresh migration not available in material 3 just for 2
private fun ListBooks(
    state: ListScreenUiState,
    onRefresh: () -> Unit,
    onDetailsClicked: (String) -> Unit,
) = SwipeRefresh(
    state = rememberSwipeRefreshState(state == LoadingFromAPI),
    onRefresh = onRefresh,
    modifier = Modifier
        .scrollable(rememberScrollState(), Orientation.Vertical)
        .systemBarsPadding()
        .padding(16.dp)
) {
    var books by remember { mutableStateOf(emptyList<Book>()) }
    (state as? UpdateSuccess)?.let {
        books = it.books
    }

    Column {
        Text(
            text = stringResource(id = R.string.book_list),
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn {
            itemsIndexed(books) { _, book ->
                Row(
                    modifier = Modifier
                        .clickable { onDetailsClicked(book.key) }
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = book.title)
                }
            }
        }
    }
}

@Composable
private fun ErrorFromApi() = Toast(R.string.api_error)

@ThemeModePreview
@FontScalePreview
@DeviceFormatPreview
@Composable
private fun ListScreenPreview() {
    TemplateTheme {
        ListBooks(
            state = UpdateSuccess(
                (1..10).map {
                    Book(it.toString(), "Preview $it")
                }
            ),
            onRefresh = {},
            onDetailsClicked = {}
        )
    }
}
