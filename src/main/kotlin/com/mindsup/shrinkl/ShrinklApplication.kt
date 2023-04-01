package com.mindsup.shrinkl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShrinklApplication

fun main(args: Array<String>) {
	runApplication<ShrinklApplication>(*args)
}
