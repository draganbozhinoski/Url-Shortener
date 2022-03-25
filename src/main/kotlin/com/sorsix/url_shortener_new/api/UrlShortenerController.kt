package com.sorsix.url_shortener_new.api

import com.sorsix.url_shortener_new.domain.Url
import com.sorsix.url_shortener_new.service.UrlService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class UrlShortenerController(val service:UrlService) {

    @GetMapping("/{url}")
    fun getRedirect(@PathVariable(name = "url") shortUrl:String,httpServletResponse: HttpServletResponse): ResponseEntity<UrlResponse> {
        return when(val response = service.findByShortUrl(shortUrl)){
            is UrlResponseSuccess -> {
                httpServletResponse.sendRedirect(response.url.url)
                ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response)
            }
            is UrlResponseError -> ResponseEntity.badRequest().body(response)
        }
    }

    @PostMapping("/save")
    fun saveUrl(@RequestBody urlRequest:UrlRequest):UrlResponse {
        return service.save(urlRequest.url)
    }

    @GetMapping
    fun getAllUrls(): MutableList<Url> {
        return service.findAll()
    }
}