package com.anilsblog.personalpage.controller

import com.anilsblog.personalpage.config.BaseTest
import com.anilsblog.personalpage.entity.NewsEntity
import com.anilsblog.personalpage.repo.NewsRepository
import com.anilsblog.personalpage.service.NewsService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.util.function.Predicate

internal class NewsControllerTest : BaseTest() {

    @MockBean
    private lateinit var newsRepository: NewsRepository

    @Autowired
    private lateinit var webClient: WebClient

    @MockBean
    lateinit var newsService: NewsService

    @BeforeEach
    fun setUp() {
        given(newsService.findAllNews()).willReturn(Flux.just(NewsEntity(1, "testNews")))
    }

    @Disabled
    fun getAllNewsBlockTest() {
        val response = this.webClient
            .get().uri("/news/allNews")
            .retrieve()
            .bodyToMono(NewsEntity::class.java)
            .block()

        println(response)
    }

    @Test
    fun getAllNewsStepVerifierTest() {
        val responseMono: Flux<NewsEntity> = this.webClient
            .get().uri("/news/allNews")
            .retrieve()
            .bodyToFlux(NewsEntity::class.java)
            .doOnNext { println(it) }

        StepVerifier.create(responseMono)
            .expectNextMatches(Predicate { r -> r.news.isNotEmpty() })
            .verifyComplete()
    }

    @Test
    fun addAnyNew() {
    }
}
