import io.micronaut.gradle.MicronautRuntime

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    id("io.micronaut.application") version "4.5.4"
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

    // Parsing av yml
    runtimeOnly("org.yaml:snakeyaml")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
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
    runtime(MicronautRuntime.LAMBDA_JAVA)
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}
