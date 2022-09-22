package io.bloco.template.features.list

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import io.bloco.template.component.TemplateButton

@Composable
fun ListScreen(listViewModel: ListViewModel, openDetailsClicked: (String) -> Unit) {
    /*TemplateButton(onClick = {
        openDetailsClicked("1")
    }) {
        Text(text = "Open Details")
    }
     */
    TextField(value = "", onValueChange = {
        Log.d("Something field", it)
    })
}