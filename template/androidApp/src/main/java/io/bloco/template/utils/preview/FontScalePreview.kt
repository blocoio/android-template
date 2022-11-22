package io.bloco.template.utils.preview

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f,
    showSystemUi = true
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 2f,
    showSystemUi = true
)

annotation class FontScalePreview