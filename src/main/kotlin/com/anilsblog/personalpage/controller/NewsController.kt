package com.anilsblog.personalpage.controller

import com.anilsblog.personalpage.entity.NewsEntity
import com.anilsblog.personalpage.exception.InputValidationException
import com.anilsblog.personalpage.service.NewsService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/news")
class NewsController(
    private val newsService: NewsService
) {
    @GetMapping("/{id}")
    fun getNews(@PathVariable id: String): Mono<NewsEntity> {
        return newsService.findNewsById(id)
    }

    @GetMapping("/allNews", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllNews(): Flux<NewsEntity> {
        println("getAll called")
        return newsService.findAllNews()
    }

    @PostMapping("/addNews")
    fun addAnyNew(
        @RequestBody newsEntity: NewsEntity
    ) {
        println("save called for $newsEntity")
        return newsService.addANews(newsEntity)
    }
}
