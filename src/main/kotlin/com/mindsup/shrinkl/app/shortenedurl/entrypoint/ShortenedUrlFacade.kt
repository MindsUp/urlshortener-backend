package com.mindsup.shrinkl.app.shortenedurl.entrypoint

import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlListResponse
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlResponse
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.shortenedurl.usecase.ShortenedUrlUseCase
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.stereotype.Component

@Component
class ShortenedUrlFacade(val useCase: ShortenedUrlUseCase) {

  fun getAllByUser(user: ShortenedUser): ShortenedUrlListResponse =
    useCase.retrieveAllFromUser(user.toDomain()).map { it.toResponse() }

  private fun ShortenedUser.toDomain() = User("", name = name)

  private fun ShortenedUrl.toResponse() =
    ShortenedUrlResponse(
      url = full, alias = alias, creationDateTime = creationDateTime.toString()
    )

}

data class ShortenedUser(
  val name: String
)
