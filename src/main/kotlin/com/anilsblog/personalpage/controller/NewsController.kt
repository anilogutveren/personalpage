package com.anilsblog.personalpage.controller

import com.anilsblog.personalpage.entity.NewsEntity
import com.anilsblog.personalpage.eventhub.EventService
import com.anilsblog.personalpage.eventhub.NewsEvent
import com.anilsblog.personalpage.service.NewsService
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
    private val newsService: NewsService,
    private val eventsService: EventService
) {
    @GetMapping("/{id}")
    fun getNews(@PathVariable id: String): Mono<NewsEntity> {
        return newsService.findNewsById(id)
    }

    @GetMapping("/allNews")
    fun getAllNews(): Flux<NewsEntity> {
        println("getAll called")

        val response = newsService.findAllNews().subscribe(
            { println("Received: $it") }, // onNext (Consumer<Object>)
            { println("ERROR: $it") }, // onError (Consumer<Throwable>)
            { println("Completed") } // onComplete (Runnable)
        ) // --> Burasi test icin yapildi.
        return newsService.findAllNews()
    }

    @PostMapping("/addNews")
    fun addAnyNew(
        @RequestBody newsEntity: NewsEntity
    ): Mono<NewsEntity> {
        println("save called for $newsEntity")
        return newsService.addANews(newsEntity)
    }

    @PostMapping("/addNewsWithEvents")
    fun addEventNew(
        @RequestBody newsEntity: NewsEntity
    ) {
        println("save called for $newsEntity")
        eventsService.sendRegisteredNews(NewsEvent(newsEntity.news))
    }
}
