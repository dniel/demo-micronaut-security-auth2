package com.example

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("localhttp")
interface LocalClient {
    @Get("/secured")
    suspend fun secured(): String
}
