package com.example

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("httpbin")
interface HttpBinClient {
    @Get("/get")
    suspend fun get(): String
}
