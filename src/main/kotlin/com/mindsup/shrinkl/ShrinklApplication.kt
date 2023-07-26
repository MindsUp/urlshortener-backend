package com.mindsup.shrinkl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShrinklApplication

fun main(args: Array<String>) {
	runApplication<ShrinklApplication>(*args)
}


// -> controller -> x <- service -> x <- repository(JPA)
// [ entrypoint(controller) ->  |  ports(interface-A) ] | [ <= (impl-A) -> core (useCase/service) -> | (class) adapter(repository) -> ] | [ gateway(jpa(Spring)) ]
//-> flow ->
//-> dependency <-
// => inject
