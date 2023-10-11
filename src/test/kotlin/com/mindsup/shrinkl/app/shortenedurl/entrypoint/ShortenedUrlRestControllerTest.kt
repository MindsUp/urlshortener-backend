package com.mindsup.shrinkl.app.shortenedurl.entrypoint

import com.fasterxml.jackson.databind.ObjectMapper
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlCreateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class ShortenedUrlRestControllerIntegrationTest {

   companion object {
     @Container
     val mongoContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:7"))
       .withCommand("mongod")
        .waitingFor(Wait.forLogMessage("(?i).*Waiting for connections.*", 1))
       .also {
         it.start()
       }

     @DynamicPropertySource
     @JvmStatic
     fun mongoProperties(registry: DynamicPropertyRegistry) {
       registry.add("spring.data.mongodb.uri", mongoContainer::getReplicaSetUrl)
     }
   }

  @Autowired
  private lateinit var mockMvc: MockMvc

  @Autowired
  private lateinit var objectMapper: ObjectMapper

  @Test
  fun testCreateShortenedUrl() {
    val request = ShortenedUrlCreateRequest(url = "https://www.example.com", alias = "example")
    val requestBody = objectMapper.writeValueAsString(request)

    mockMvc.perform(
      MockMvcRequestBuilders.post("/v1/shortened-url")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody)
    )
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.jsonPath("$.url").value(request.url))
      .andExpect(MockMvcResultMatchers.jsonPath("$.alias").value(request.alias))
  }

  @Test
  fun testGetShortenedUrlByAlias() {
    val alias = "example"

    mockMvc.perform(MockMvcRequestBuilders.get("/v1/shortened-url/{alias}", alias))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.jsonPath("$.alias").value(alias))
  }

  @Test
  fun testGetAllShortenedUrlsByUser() {
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/shortened-url/"))
      .andExpect(MockMvcResultMatchers.status().isOk)
  }
}
