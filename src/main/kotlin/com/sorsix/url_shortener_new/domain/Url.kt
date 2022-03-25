package com.sorsix.url_shortener_new.domain

import javax.persistence.*

@Entity
@Table(name = "urls")
data class Url(
    @Id
    val shortUrl:String = "",
    val url:String = "",
)
