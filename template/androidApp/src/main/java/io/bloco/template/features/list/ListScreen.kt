package io.bloco.template.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.bloco.template.R
import io.bloco.template.component.Toast

@Composable
fun ListScreen(
    listViewModel: ListViewModel,
    openDetailsClicked: (String) -> Unit,
) {
    val bookListUpdateState by listViewModel.bookListUpdateState.collectAsState()

    when (val state = bookListUpdateState) {
        ListViewModel.BookListUpdateState.ErrorFromAPI -> Toast(R.string.api_error)
        ListViewModel.BookListUpdateState.LoadingFromAPI -> Unit
        is ListViewModel.BookListUpdateState.UpdateSuccess -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = bookListUpdateState == ListViewModel.BookListUpdateState.LoadingFromAPI),
                onRefresh = { listViewModel.refresh() },
                modifier = Modifier
                    .scrollable(rememberScrollState(), Orientation.Vertical)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.book_list),
                        style = MaterialTheme.typography.h3
                    )
                    LazyColumn {
                        itemsIndexed(state.books) { _, book ->
                            Row(
                                modifier = Modifier
                                    .clickable { openDetailsClicked(book.key) }
                                    .padding(vertical = 16.dp)
                            ) {
                                Text(text = book.title)
                            }
                        }
                    }
                }
            }
        }
    }
}
