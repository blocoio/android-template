package io.bloco.template.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineModule::class]
)
class CoroutinesTestModule : CoroutineModule() {
    override val backgroundDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main
}