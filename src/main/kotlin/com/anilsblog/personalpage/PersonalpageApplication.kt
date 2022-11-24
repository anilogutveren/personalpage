package com.anilsblog.personalpage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication  //(exclude={MongoAutoConfiguration.class})
class PersonalpageApplication

fun main(args: Array<String>) {
    runApplication<PersonalpageApplication>(*args)
}
