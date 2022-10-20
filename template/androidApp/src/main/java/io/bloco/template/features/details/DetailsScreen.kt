package io.bloco.template.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.bloco.template.R
import io.bloco.template.component.Toast

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel) {
    val bookListUpdateState by detailsViewModel.bookDetailsUpdateState.collectAsState()

    when (val state = bookListUpdateState) {
        DetailsViewModel.APIRequestState.ErrorFromAPI -> Toast(R.string.api_error)
        DetailsViewModel.APIRequestState.LoadingFromAPI -> Unit
        is DetailsViewModel.APIRequestState.Success -> {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.book_title, state.book.title),
                    style = MaterialTheme.typography.h5
                )

            }
        }
    }
}