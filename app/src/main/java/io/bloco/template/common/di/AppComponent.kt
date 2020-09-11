package io.bloco.template.common.di

import dagger.Component
import io.bloco.template.App
import io.bloco.template.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
    ]
)

interface AppComponent {
    fun activityComponent(): ActivityComponent
    fun inject(app: App)
}