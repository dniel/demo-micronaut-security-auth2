package com.example
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured

@Controller
@ExecuteOn(TaskExecutors.BLOCKING)
class LocalController {
    @Get("/secured")
    @Secured("secured")
    fun getHello(): String = "Hello World"
}
