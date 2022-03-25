package com.sorsix.url_shortener_new.repository

import com.sorsix.url_shortener_new.domain.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : JpaRepository<Url,String> {
}