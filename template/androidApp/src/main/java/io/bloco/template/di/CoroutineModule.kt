package io.bloco.template.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bloco.core.commons.BackgroundDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @BackgroundDispatcher
    fun backgroundContext(): CoroutineContext = Dispatchers.IO
}
