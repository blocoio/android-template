package io.bloco.template.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.bloco.datatest.MockOpenLibraryApi
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
class DataTestModule : DataModule() {

    override val baseUrl = "localhost"

    override fun internalHttpClientEngine(): HttpClientEngineFactory<*> =
        object : HttpClientEngineFactory<HttpClientEngineConfig> {
            override fun create(block: HttpClientEngineConfig.() -> Unit) = MockOpenLibraryApi.engine
        }
}
