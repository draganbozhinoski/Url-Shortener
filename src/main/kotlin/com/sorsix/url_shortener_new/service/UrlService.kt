package com.sorsix.url_shortener_new.service

import com.sorsix.url_shortener_new.api.UrlResponse
import com.sorsix.url_shortener_new.api.UrlResponseError
import com.sorsix.url_shortener_new.api.UrlResponseSuccess
import com.sorsix.url_shortener_new.domain.Url
import com.sorsix.url_shortener_new.repository.UrlRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class UrlService(val repository: UrlRepository) {
    fun generateShortUrl():String {
        val length = Random.nextInt(1,10)
        val allowed = ('a'..'z') + ('A'..'Z') + (1..9)
        return (1..length)
            .map { allowed.random() }
            .joinToString("")
    }
    fun save(url:String):UrlResponse {
        val shortUrl = generateShortUrl()
        //ovde dali e potrebno da se pravi proverka dali postoi url-ot vo baza
        //so ogled na toa deka aplikacijata gi brise site urls vo 4 casot sabajle
        return when{
            repository.findByIdOrNull(shortUrl) != null -> UrlResponseError("Short url already in the database, please make another request")
            else -> {
                val saveUrl:Url = Url(shortUrl,url)
                repository.save(saveUrl)
                UrlResponseSuccess(saveUrl)
            }
        }
    }
    fun findByShortUrl(shortUrl:String): UrlResponse {
        return when(val url = repository.findByIdOrNull(shortUrl)){
            null -> UrlResponseError("We can't find shortened url, please try again later..")
            else -> UrlResponseSuccess(url)
        }
    }
    fun findAll(): MutableList<Url> {
        return repository.findAll()
    }
    fun clearDatabase(){
        repository.deleteAll()
        println("Database cleared")
    }
}