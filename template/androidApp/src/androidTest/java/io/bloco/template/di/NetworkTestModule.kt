package io.bloco.template.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.bloco.data_test.MockOpenLibraryApi
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class NetworkTestModule: NetworkModule() {

    override val baseUrl = "localhost"

    override fun internalHttpClientEngine(): HttpClientEngineFactory<*> =
        object : HttpClientEngineFactory<HttpClientEngineConfig> {
            override fun create(block: HttpClientEngineConfig.() -> Unit) = MockOpenLibraryApi.engine
        }
}