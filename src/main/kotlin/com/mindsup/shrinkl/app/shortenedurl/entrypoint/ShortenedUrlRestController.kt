package com.mindsup.shrinkl.app.shortenedurl.entrypoint

import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlCreateRequest
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlListResponse
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlResponse
import org.springframework.web.bind.annotation.*


@RequestMapping("/v1/shortened-url")
@RestController
class ShortenedUrlRestController(val facade:ShortenedUrlFacade) {

  companion object {
    private val USER = ShortenedUser("DEFAULT")
  }

  @GetMapping
  fun getAll(): ShortenedUrlListResponse = this.facade.getAll()

  @GetMapping("/")
  fun getAllByUser(): ShortenedUrlListResponse = this.facade.getAllByUser(USER)

  @GetMapping("/{id}")
  fun getById(@PathVariable id: String): ShortenedUrlResponse = this.facade.getById(id)

  @PostMapping
  fun create(@RequestBody request: ShortenedUrlCreateRequest): ShortenedUrlResponse = this.facade.create(request)
}
