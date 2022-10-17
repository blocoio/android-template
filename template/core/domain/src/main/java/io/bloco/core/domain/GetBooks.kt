package io.bloco.core.domain

import io.bloco.core.data.network.OpenLibraryHttpClient
import io.bloco.core.data.network.OpenLibraryService
import java.net.http.HttpClient
import javax.inject.Inject

class GetBooks @Inject constructor(
    client: OpenLibraryService
) {
}