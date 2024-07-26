package com.consultantree.view_tracker.data.external.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val NETWORK_TIME_OUT = 5_000L

val httpClient = HttpClient(CIO) {
    expectSuccess = true

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    engine {
        endpoint {
            keepAliveTime = NETWORK_TIME_OUT
            connectTimeout = NETWORK_TIME_OUT
            connectAttempts = 3
        }
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
        }
        headers {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }
        }
        level = LogLevel.ALL
    }
}
