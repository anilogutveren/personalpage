package com.anilsblog.personalpage.repo

import com.anilsblog.personalpage.entity.NewsEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository : ReactiveMongoRepository<NewsEntity, String>
