package io.bloco.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.bloco.template.core.designsystem.theme.TemplateTheme
import io.bloco.template.features.list.ListScreen
import io.bloco.template.navigation.TemplateNaveHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TemplateTheme { TemplateNaveHost() }
        }
    }
}