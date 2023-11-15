package com.mindsup.shrinkl.app.shortenedurl.entrypoint

import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlCreateRequest
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlListResponse
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlResponse
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RequestMapping("/v1/shortened-url")
@RestController
class ShortenedUrlRestController(val facade:ShortenedUrlFacade) {

  companion object {
    private val USER = ShortenedUser("11111", "DEFAULT")
  }

  @GetMapping("")
  fun getAllByUser(): ShortenedUrlListResponse = this.facade.getAllByUser(USER)

  @GetMapping("/{alias}")
  fun getByAlias(@PathVariable alias: String): ShortenedUrlResponse = this.facade.getByAlias(alias)

  @PostMapping
  fun create(@RequestBody request: ShortenedUrlCreateRequest): ShortenedUrlResponse = this.facade.create(request, USER)

  @DeleteMapping("/{alias}")
  fun deleteByAlias(@PathVariable alias: String): ResponseEntity<Void> {
    this.facade.deleteByAlias(alias)
    return ResponseEntity.noContent().build()
  }
}
