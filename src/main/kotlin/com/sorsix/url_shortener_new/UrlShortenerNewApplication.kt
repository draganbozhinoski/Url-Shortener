package com.sorsix.url_shortener_new

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class UrlShortenerNewApplication

fun main(args: Array<String>) {
    runApplication<UrlShortenerNewApplication>(*args)
}
