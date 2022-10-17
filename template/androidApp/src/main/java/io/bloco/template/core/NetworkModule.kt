package io.bloco.template.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bloco.core.data.network.OpenLibraryHttpClient
import io.bloco.core.data.network.httpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @OpenLibraryHttpClient
    @Provides
    fun ktorHttpClient() = httpClient()
}

//TODO: Rever esta dependência
