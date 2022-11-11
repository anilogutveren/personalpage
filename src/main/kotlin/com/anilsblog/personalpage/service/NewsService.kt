package com.anilsblog.personalpage.service

import com.anilsblog.personalpage.entity.NewsEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface NewsService {
    fun findNewsById(id: String): Mono<NewsEntity>
    fun findAllNews(): Flux<NewsEntity>
    fun addANews(newsEntity: NewsEntity)
}
