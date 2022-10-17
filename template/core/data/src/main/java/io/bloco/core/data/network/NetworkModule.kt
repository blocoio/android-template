package io.bloco.core.data.network

import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import javax.inject.Qualifier

class NetworkModule {

    @OpenLibraryHttpClient
    @Provides
    fun ktorHttpClient(): HttpClient = HttpClient() {
        expectSuccess = true

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "openlibrary.org"
            }
        }
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OpenLibraryHttpClient