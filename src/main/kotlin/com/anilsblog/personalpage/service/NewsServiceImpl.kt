package com.anilsblog.personalpage.service

import com.anilsblog.personalpage.entity.NewsEntity
import com.anilsblog.personalpage.eventhub.EventService
import com.anilsblog.personalpage.eventhub.NewsEvent
import com.anilsblog.personalpage.exception.EntityNotFoundException
import com.anilsblog.personalpage.repo.NewsRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class NewsServiceImpl(private val newsRepository: NewsRepository, private val eventService: EventService) : NewsService {

    override fun findNewsById(id: String): Mono<NewsEntity> {
        return newsRepository.findById(id) ?: throw EntityNotFoundException(id)
    }

    override fun findAllNews(): Flux<NewsEntity> {
        return newsRepository.findAll()
    }

    override fun addANews(newsEntity: NewsEntity): Mono<NewsEntity> {
        return newsRepository.save(newsEntity).doOnSuccess { eventService.sendRegisteredNews(NewsEvent(newsEntity.news)) }
    }
}
