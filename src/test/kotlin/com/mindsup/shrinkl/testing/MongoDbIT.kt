package com.mindsup.shrinkl.testing

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
abstract class MongoDbIT {

    companion object {
        @Container
        @JvmStatic
        var container = MongoDBContainer(DockerImageName.parse("mongo:5"))

        @DynamicPropertySource
        @JvmStatic
        fun mongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { container.replicaSetUrl }
        }

    }

}