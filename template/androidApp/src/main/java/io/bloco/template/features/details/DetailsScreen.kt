package io.bloco.template.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.bloco.template.R
import io.bloco.template.component.Toast

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel, id: String) {
    val bookListUpdateState by detailsViewModel.bookDetailsUpdateState.collectAsState()
    val bookDetails by detailsViewModel.bookDetails.collectAsState()

    //TODO: Talvez a solução seja https://medium.com/scalereal/providing-assistedinject-supported-viewmodel-for-composable-using-hilt-ae973632e29a
    LaunchedEffect(key1 = Unit, block = {
        detailsViewModel.sendBookId(id = id)
    })

    when (bookListUpdateState) {
        DetailsViewModel.APIRequestState.ErrorFromAPI -> Toast(R.string.api_error)
        DetailsViewModel.APIRequestState.LoadingFromAPI -> Unit
        DetailsViewModel.APIRequestState.Success -> {
            Column {
                Text(
                    text = bookDetails.title,
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
}