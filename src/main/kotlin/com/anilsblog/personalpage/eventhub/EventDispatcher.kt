package com.anilsblog.personalpage.eventhub

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Component

interface EventDispatcher {
    fun sendRegisteredNewsEvent(event: NewsEvent)
}

const val NEWS_REGISTERED = "newsRegistered-out-0"

@Component
class EventDispatcherAzure(
    private val objectMapper: ObjectMapper,
    private val bridge: StreamBridge
) : EventDispatcher {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun sendRegisteredNewsEvent(event: NewsEvent) {
        val serializedEvent = objectMapper.writeValueAsString(event)
        try {
            bridge.send(NEWS_REGISTERED, GenericMessage(serializedEvent))
                .also { logger.info("Sent $event") }
        } catch (e: Exception) {
            logger.error("Error while sending event $event")
            throw e
        }
    }
}
