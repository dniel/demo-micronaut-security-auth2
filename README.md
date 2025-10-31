# Micronaut with oauth authorization and authentication for backend and clients.

## Description
This application is a simple example of how to use Micronaut with oauth authorization and authentication for backend and clients.
It uses the mock-idp as the identity provider to authenticate and authorize the requests.

## The output of the application is:

```terminaloutput
Well-known URL: http://localhost:8081/default/.well-known/openid-configuration
Well-known endpoint response: {
  "issuer" : "http://localhost:8081/default",
  "authorization_endpoint" : "http://localhost:8081/default/authorize",
  "end_session_endpoint" : "http://localhost:8081/default/endsession",
  "revocation_endpoint" : "http://localhost:8081/default/revoke",
  "token_endpoint" : "http://localhost:8081/default/token",
  "userinfo_endpoint" : "http://localhost:8081/default/userinfo",
  "jwks_uri" : "http://localhost:8081/default/jwks",
  "introspection_endpoint" : "http://localhost:8081/default/introspect",
  "response_types_supported" : [ "code", "none", "id_token", "token" ],
  "response_modes_supported" : [ "query", "fragment", "form_post" ],
  "subject_types_supported" : [ "public" ],
  "id_token_signing_alg_values_supported" : [ "ES256", "ES384", "RS256", "RS384", "RS512", "PS256", "PS384", "PS512" ],
  "code_challenge_methods_supported" : [ "plain", "S256" ]
}
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_ 
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
12:15:27.608 [main] DEBUG i.m.s.a.AuthenticationModeCondition - CookieBasedAuthenticationModeCondition} is not fulfilled because micronaut.security.authentication is not set.
12:15:27.619 [main] DEBUG i.m.s.o.routes.OauthRouteBuilder - No Oauth controllers found. Skipping registration of routes
12:15:27.788 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 452ms. Server Running: http://localhost:8080
12:15:27.790 [main] INFO  com.example.HttpClientLoggingFilter - HttpLoggingFilter initialized.
12:15:27.813 [main] INFO  com.example.HttpClientLoggingFilter - >>> HttpLoggingFilter (serviceId=httpbin): intercepting GET https://httpbin.org/get
12:15:27.820 [main] INFO  com.example.HttpClientLoggingFilter - >>> HttpLoggingFilter (serviceId=unknown): intercepting POST http://localhost:8081/default/token
12:15:36.536 [default-eventLoopGroup-3-2] INFO  com.example.HttpClientLoggingFilter - >>> HttpLoggingFilter (serviceId=localhttp): intercepting GET http://localhost:8080/secured
12:15:36.636 [default-eventLoopGroup-3-4] DEBUG i.m.s.a.AuthenticationModeCondition - CookieBasedAuthenticationModeCondition} is not fulfilled because micronaut.security.authentication is not set.
12:15:36.642 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.reader.HttpHeaderTokenReader - Looking for bearer token in Authorization header
12:15:36.644 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.reader.DefaultTokenResolver - Token found in request GET /secured
12:15:36.668 [default-eventLoopGroup-3-4] INFO  com.example.HttpClientLoggingFilter - >>> HttpLoggingFilter (serviceId=mock-idp): intercepting GET http://localhost:8081/default/jwks
12:15:36.688 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.j.s.jwks.JwksSignatureUtils - JWT Key ID: default
12:15:36.688 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.j.s.jwks.JwksSignatureUtils - JWK Set Key IDs: default
12:15:36.689 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.j.s.jwks.JwksSignatureUtils - Found 1 matching JWKs
12:15:36.692 [default-eventLoopGroup-3-4] DEBUG i.m.s.t.j.n.ReactiveJwksSignature - JWT Signature verified: eyJraWQiOiJkZWZhdWx0IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJjbGllbnQtaWQiLCJhdWQiOiJkZWZhdWx0IiwibmJmIjoxNzYxOTA5MzI3LCJzY29wZSI6InNlY3VyZWQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODEvZGVmYXVsdCIsImV4cCI6MTc2MTkxMjkyNywiaWF0IjoxNzYxOTA5MzI3LCJqdGkiOiIzNTJlYjc3Ni0xNTZkLTRlN2QtODg4YS1hZjI0OTVmODFjOWEiLCJ0aWQiOiJkZWZhdWx0In0.kMrN-twlTCyC4ABYw-oF5Jrwmzi3APujnvGzGwXkaSZNH18tahjkf6GUS7Bg1EPvjNrr_Pt5Gf0MYY_MvaSnnlo2XT5_9l5XZ5RoIclbiZCJzGDE1oENTtl3G8Yk5ISkwxUNGfTlmPoTIvB8skVXsX7WO3py9Rqq58ybj_FDKkR2aiZVqX23aRzj_uyZX5G-_vrklXh999Ae-hppm4gk4SyM19QyH-IqLqlvUsw7I8G_QbRiCAfSDYlydoSln8l4gDEiyzfBYzeXU85C4-XoA7oO5tWZkNOA5A_CpHvljxRHVda8cyIchXNOcMVndjgc7mbSvV_pjetxhLFS80TzJQ
12:15:36.696 [default-eventLoopGroup-3-4] DEBUG i.m.security.filters.SecurityFilter - Attributes: sub=>client-id, aud=>[default], nbf=>Fri Oct 31 12:15:27 CET 2025, scope=>secured, iss=>http://localhost:8081/default, exp=>Fri Oct 31 13:15:27 CET 2025, iat=>Fri Oct 31 12:15:27 CET 2025, jti=>352eb776-156d-4e7d-888a-af2495f81c9a, tid=>default
12:15:36.699 [default-eventLoopGroup-3-4] DEBUG i.m.security.rules.IpPatternsRule - One or more of the IP patterns matched the host address [127.0.0.1]. Continuing request processing.
12:15:36.702 [default-eventLoopGroup-3-4] DEBUG i.m.s.rules.AbstractSecurityRule - The given roles [[secured, isAnonymous(), isAuthenticated()]] matched one or more of the required roles [[secured]]. Allowing the request
12:15:36.702 [default-eventLoopGroup-3-4] DEBUG i.m.security.filters.SecurityFilter - Authorized request GET /secured. The rule provider io.micronaut.security.rules.SecuredAnnotationRule authorized the request.
Secured endpoint response: Hello World
12:15:36.753 [Thread-0] INFO  io.micronaut.runtime.Micronaut - Embedded Application shutting down

Process finished with exit code 0
```
