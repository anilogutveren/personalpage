package com.anilsblog.personalpage.exception

class InputValidationException : RuntimeException {
    private val errorCode = 100
    override val message = "Invalid Input"
    private var input: String

    constructor(input: String) {
        this.input = input
    }
}
