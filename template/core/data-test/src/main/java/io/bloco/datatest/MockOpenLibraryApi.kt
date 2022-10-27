package io.bloco.datatest

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

object MockOpenLibraryApi {

    private val responseHeaders =
        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    private val responses = mutableMapOf<String, ResponseValue>()

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                responses[request.url.encodedPathAndQuery]?.let { response ->
                    respond(
                        response.content,
                        response.statusCode,
                        responseHeaders,
                    )
                } ?: throw AssertionError("No response specified for $request")
            }
        }
    }

    val engine = client.engine

    fun giveResponse(request: String, response: ResponseValue) {
        responses[request] = response
    }

    data class ResponseValue(val statusCode: HttpStatusCode, val content: String = "")
}
