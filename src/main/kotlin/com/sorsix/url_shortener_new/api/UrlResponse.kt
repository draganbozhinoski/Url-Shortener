package com.sorsix.url_shortener_new.api

import com.sorsix.url_shortener_new.domain.Url

sealed interface UrlResponse

data class UrlResponseSuccess(val url: Url) : UrlResponse
data class UrlResponseError(val message:String) : UrlResponse