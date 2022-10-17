package io.bloco.template.features.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel, id: String?) {
    Text("Received: $id")
}