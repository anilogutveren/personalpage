package com.anilsblog.personalpage.service

import com.anilsblog.personalpage.entity.NewsEntity
import com.anilsblog.personalpage.repo.NewsRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class NewsServiceImpl(private val newsRepository: NewsRepository) : NewsService {

    override fun findNewsById(id: String): Mono<NewsEntity> {
        return newsRepository.findById(id)
    }

    override fun findAllNews(): Flux<NewsEntity> {
        return newsRepository.findAll()
    }

    override fun addANews(newsEntity: NewsEntity) {
        newsRepository.save(newsEntity)
    }
}
