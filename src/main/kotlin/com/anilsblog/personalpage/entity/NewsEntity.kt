package com.anilsblog.personalpage.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class NewsEntity(

    @Id
    val id: Long,
    val news: String
)
