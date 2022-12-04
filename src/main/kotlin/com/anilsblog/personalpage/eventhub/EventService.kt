package com.anilsblog.personalpage.eventhub

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventDispatcher: EventDispatcher
) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @EventListener
    @Async
    fun sendRegisteredNews(event: NewsEvent) {
        try {
            eventDispatcher.sendRegisteredNewsEvent(event)
        } catch (e: Exception) {
            logger.error("Sending technical services changed failed. Reason: $e")
            throw e
        }
    }
}
