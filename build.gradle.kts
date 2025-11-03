import io.micronaut.gradle.MicronautRuntime

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("io.micronaut.application") version "4.6.1"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

version = "0.1"
group = "com.example"

val micronautVersion: String by project
val jvmVersion = "23" // 17 does not work, 19+ works

tasks.register("logJavaVersion") {
    doLast {
        println("Gradle is running on Java version: ${JavaVersion.current()}")
        println("Java vendor: ${System.getProperty("java.vendor")}")
        println("Java home: ${System.getProperty("java.home")}")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-http-server-netty")

    ksp("io.micronaut.security:micronaut-security-annotations")
    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("io.micronaut.security:micronaut-security-jwt")

    implementation("io.micronaut.security:micronaut-security")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("no.nav.security:mock-oauth2-server:3.0.0")

    // Parsing av yml
    runtimeOnly("org.yaml:snakeyaml")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.withType<JavaCompile>().configureEach {
    options.release.set(21) // makes sure Java compilation targets the same
}

kotlin {
    jvmToolchain(21)
}
application {
    mainClass = "com.example.ApplicationKt"
}

graalvmNative.toolchainDetection = false

micronaut {
    version(micronautVersion)
    runtime(MicronautRuntime.NETTY)
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}
