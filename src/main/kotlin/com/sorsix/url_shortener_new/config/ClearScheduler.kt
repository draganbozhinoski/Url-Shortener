package com.sorsix.url_shortener_new.config

import com.sorsix.url_shortener_new.service.UrlService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ClearScheduler(val service:UrlService) {

    @Scheduled(cron = "0 30 4 * * ?")
    fun clearDatabase(){
        println("Clearing database..")
        service.clearDatabase()
    }
}