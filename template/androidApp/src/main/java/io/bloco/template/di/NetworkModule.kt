package io.bloco.template.di

import androidx.annotation.VisibleForTesting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bloco.core.commons.ApiUrl
import io.bloco.core.commons.endpoints.OpenLibraryEndpoint
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @VisibleForTesting
    internal open val baseUrl = OpenLibraryEndpoint.baseUrl

    @Provides
    @ApiUrl
    fun apiUrl(): String = baseUrl

    protected open fun internalHttpClientEngine(): HttpClientEngineFactory<*> = Android

    @Provides
    fun httpClientEngine(): HttpClientEngineFactory<*> = internalHttpClientEngine()
}
