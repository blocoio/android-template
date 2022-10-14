package io.bloco.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.header
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Qualifier

val jsonSerializer by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
    }
}

fun httpClient(): HttpClient = HttpClient(CIO) {
    expectSuccess = true

    install(ContentNegotiation) {
        json(jsonSerializer)
    }

    install(ResponseObserver) {
        onResponse { response ->
            println(response.status.value.toString())
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, Json)
        url {
            protocol = URLProtocol.HTTPS
            host = "openlibrary.org"
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OpenLibraryHttpClient