package com.example

import io.micronaut.http.BasicHttpAttributes
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.ClientFilter
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory

/**
 * Logs the service ID of the request.
 *
 * If a named service-specific client is configured (i.e. with "micronaut.http.services.foo.*") with a
 * name that matches the name used for security configuration (i.e. "micronaut.security.token.jwt.signatures.jwks.foo.*")
 * then that client will be used for the request. Otherwise, a default client will be used.
 *
 * See
 * https://github.com/micronaut-projects/micronaut-security/blob/cd5a2faf8399ea4e8e9e4317b70b346779436400/security-jwt/src/main/java/io/micronaut/security/token/jwt/signature/jwks/HttpClientJwksClient.java
 *
 * FIXME This must be a bug in Micronaut HTTP Client?
 * The annotation @ClientFilter("httpbin") does not work, but @Filter("httpbin") does.
 * with ClientFilter the serviceId is ignored, and does not limit which request that its
 * triggering on.
 */
@ClientFilter
/**
 * FIXME This must be another bug in Micronaut HTTP Client?
 * The Filter annotation does not work, client filters with Filter
 * annotation does not intercept requests.
 *
 * FIXME Another strange quirk of Micronaut HTTP Client.
 * If both ClientFilter and Filter annotations are present, the serviceId
 * on Filter is applied and the filter intercepts requests as expected only on
 * request from an service with that serviceId.
 */
@Filter
class HttpClientLoggingFilter : HttpClientFilter {
    private val log = LoggerFactory.getLogger(HttpClientLoggingFilter::class.java)

    init {
        log.info("HttpLoggingFilter initialized.")
    }

    override fun doFilter(
        request: MutableHttpRequest<*>,
        chain: ClientFilterChain,
    ): Publisher<out HttpResponse<*>> {
        val serviceId =
            if (BasicHttpAttributes.getServiceId(request).isPresent) {
                BasicHttpAttributes.getServiceId(request).get()
            } else {
                "unknown"
            }

        log.info(">>> HttpLoggingFilter (serviceId=$serviceId): intercepting ${request.method} ${request.uri}")
        return chain.proceed(request)
    }
}
