package com.anilsblog.personalpage.exceptionhandler

import com.anilsblog.personalpage.dto.EntityNotFoundResponse
import com.anilsblog.personalpage.dto.InputValidationFailedResponse
import com.anilsblog.personalpage.exception.EntityNotFoundException
import com.anilsblog.personalpage.exception.InputValidationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class WebExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(InputValidationException::class)
    fun handleInputValidationException(ex: InputValidationException): ResponseEntity<InputValidationFailedResponse> {
        log.error("Invalid Input: ${ex.message}", ex)
        val response = InputValidationFailedResponse(100, ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<EntityNotFoundResponse> {
        log.error("Invalid Input: ${ex.message}", ex)
        val response = EntityNotFoundResponse(100, ex.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }
}
