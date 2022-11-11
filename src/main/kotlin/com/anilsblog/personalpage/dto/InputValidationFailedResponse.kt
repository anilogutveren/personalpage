package com.anilsblog.personalpage.dto

data class InputValidationFailedResponse(

    private val errorCode: Int,
    private val message: String
)
