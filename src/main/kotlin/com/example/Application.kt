package com.example

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.Micronaut.run
import jakarta.inject.Singleton
import no.nav.security.mock.oauth2.MockOAuth2Server
import no.nav.security.mock.oauth2.token.DefaultOAuth2TokenCallback
import kotlin.system.exitProcess

@Singleton
object Api

suspend fun main(args: Array<String>) {
    /**
     * Configure a mock oauth2 server that this demo
     * will use to simulate a remote OAuth IDP.
     */
    val server = MockOAuth2Server()
    server.start(8081)
    val wellKnownUrl = server.wellKnownUrl("default")
    println("Well-known URL: $wellKnownUrl")

    val client = HttpClient.create(wellKnownUrl.toUrl())
    val response = client.toBlocking().retrieve(wellKnownUrl.toString())
    println("Well-known endpoint response: $response")

    // close the client when done.
    client.close()

    // configure claims
    server.enqueueCallback(
        DefaultOAuth2TokenCallback(
            claims = mapOf("scope" to "secured"),
        ),
    )

    /**
     * Start the Micronaut server.
     */
    val context: ApplicationContext = run(*args)

    /**
     * Call HttpBin, it is configured to use the mock
     * IDP as an authentication server and will need to
     * retrieve an access token from it first and
     * add as a Bearer token to the request.
     */
    val httpbinClient = context.getBean(HttpBinClient::class.java)
    httpbinClient.get()

    try {
        val localClient = context.getBean(LocalClient::class.java)
        val body = localClient.secured()
        println("Secured endpoint response: $body")
    } catch (e: Exception) {
        println("Error calling secured endpoint: ${e.message}")
    }

    /**
     * Cleanup and exit the demo.
     * Shutdown server and mock oauth server
     */
    server.shutdown()
    context.close()
    exitProcess(0)
}
