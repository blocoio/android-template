package io.bloco.template.utils.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "day theme",
    group = "theme mode",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO,
)

@Preview(
    name = "night theme",
    group = "theme mode",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)

annotation class ThemeModePreview
