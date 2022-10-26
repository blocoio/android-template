package io.bloco.data_test

import io.bloco.core.commons.endpoints.OpenLibraryEndpoint
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

object MockOpenLibraryApi {

    private val responseHeaders =
        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    private var statusCode: HttpStatusCode = HttpStatusCode.Accepted
    private val statusCodeIsSuccess
        get() = statusCode == HttpStatusCode.Accepted

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.encodedPathAndQuery) {
                    OpenLibraryEndpoint.search("Android") -> {
                        respond(
                            DataTestResources.bookListJson(statusCodeIsSuccess),
                            statusCode,
                            responseHeaders,
                        )
                    }
                    else -> {
                        error("Unhandled ${request.url.encodedPathAndQuery}")
                    }
                }
            }
        }
    }

    val engine = client.engine

    fun giveSuccess() {
        statusCode = HttpStatusCode.Accepted
    }

    fun giveInternetError() {
        statusCode = HttpStatusCode.GatewayTimeout
    }
}
