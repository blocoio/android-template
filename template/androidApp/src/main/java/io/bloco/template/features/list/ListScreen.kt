package io.bloco.template.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.bloco.template.R
import io.bloco.template.component.Toast

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(listViewModel: ListViewModel, openDetailsClicked: (String) -> Unit) {
    val bookListUpdateState by listViewModel.bookListUpdateState.collectAsState()
    val books = listViewModel.books.collectAsState()

    when (bookListUpdateState) {
        ListViewModel.BookListUpdateState.ErrorFromAPI -> Toast(R.string.api_error)
        ListViewModel.BookListUpdateState.LoadingFromAPI -> Unit
        ListViewModel.BookListUpdateState.UpdateSuccess -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = bookListUpdateState == ListViewModel.BookListUpdateState.LoadingFromAPI),
                onRefresh = { listViewModel.refresh() }) {

                LazyColumn {
                    itemsIndexed(books.value) { _, book ->
                        Row {
                            ListItem(
                                modifier = Modifier.clickable {
                                    openDetailsClicked(book.key)
                                },
                                text = { Text(text = book.title) }
                            )
                        }
                    }
                }
            }
        }
    }
}
