package io.bloco.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import io.bloco.template.navigation.BaseViewModelFactoryProvider
import io.bloco.template.navigation.TemplateNaveHost
import io.bloco.template.theme.TemplateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider : BaseViewModelFactoryProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TemplateTheme { TemplateNaveHost() } }
    }
}
