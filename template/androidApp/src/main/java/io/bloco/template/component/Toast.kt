package io.bloco.template.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.bloco.template.R

@Composable
fun Toast(stringId: Int) {
    val context = LocalContext.current
    android.widget.Toast.makeText(
        context, stringResource(id = stringId), android.widget.Toast.LENGTH_LONG
    ).show()
}