package io.bloco.template.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.bloco.template.core.designsystem.theme.Purple200
import io.bloco.template.core.designsystem.theme.Purple500
import io.bloco.template.core.designsystem.theme.Purple700
import io.bloco.template.core.designsystem.theme.Shapes
import io.bloco.template.core.designsystem.theme.Teal200

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun TemplateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes
        ) {
            Scaffold { content.invoke(it) }
        }
    }

}