rootProject.name = "demo"

pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    plugins {
        id("com.google.devtools.ksp") version kspVersion
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("1.0.0")
}
