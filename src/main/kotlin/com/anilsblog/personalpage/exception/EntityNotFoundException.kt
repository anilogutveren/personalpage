package com.anilsblog.personalpage.exception

class EntityNotFoundException : RuntimeException {
    private val errorCode = 100
    override val message = "Entity was not found"
    private var input: Any

    constructor(input: String) {
        this.input = input
    }
}
