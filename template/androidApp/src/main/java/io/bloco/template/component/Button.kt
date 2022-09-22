package io.bloco.template.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.bloco.template.core.designsystem.theme.normalPadding
import io.bloco.template.core.designsystem.theme.smallButtonHeight

@Composable
fun TemplateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = smallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        contentPadding = PaddingValues(normalPadding),
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                content()
            }
        }
    )
}
