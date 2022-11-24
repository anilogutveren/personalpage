package com.anilsblog.personalpage.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import java.util.concurrent.TimeUnit
import com.mongodb.client.MongoClients as MongoClientsMongoClients

@Configuration
@EnableReactiveMongoRepositories("com.anilsblog.personalpage.repo")
class MongoConfiguration : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    private lateinit var mongodbUri: String

    @Value("\${spring.data.mongodb.database}")
    private lateinit var mongodbDatabase: String

    override fun getDatabaseName() = mongodbDatabase

    override fun reactiveMongoClient() = mongoClient()

    @Bean
    fun mongoClient(): MongoClient = MongoClientSettings.builder()
        .applyToConnectionPoolSettings { it.maxConnectionIdleTime(1L, TimeUnit.MINUTES).build() }
        .applyConnectionString(ConnectionString(mongodbUri))
        .build()
        .let { MongoClients.create(it) }

    @Bean
    fun reactiveMongoTemplate() = ReactiveMongoTemplate(mongoClient(), databaseName)

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoClientSettings = MongoClientSettings.builder()
            .applyToConnectionPoolSettings { it.maxConnectionIdleTime(1L, TimeUnit.MINUTES).build() }
            .applyConnectionString(ConnectionString(mongodbUri))
            .build()

        val client = MongoClientsMongoClients.create(mongoClientSettings)
        return MongoTemplate(client, databaseName)
    }
}
