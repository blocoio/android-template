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
/**
 * All of tests code should run in the same dispatcher. For that, we need to
 * override the original background dispatcher (that was defined in Coroutine Module to
 * Dispatchers.IO) to Dispatchers.Main.
 */
class CoroutinesTestModule : CoroutineModule() {
    override val backgroundDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main
}
