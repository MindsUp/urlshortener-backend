package com.mindsup.shrinkl.app.shortenedurl.entrypoint

import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlCreateRequest
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlListResponse
import com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload.ShortenedUrlResponse
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrlCreation
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUser
import com.mindsup.shrinkl.core.shortenedurl.usecase.ShortenedUrlUseCase
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class ShortenedUrlFacade(val useCase: ShortenedUrlUseCase) {

  fun getAllByUser(user: ShortenedUser): ShortenedUrlListResponse =
    useCase.retrieveAllFromUser(user.toDomain())
      .map { it.toResponse() }
      .toListResponse()

  fun getByAlias(alias: String): ShortenedUrlResponse = useCase.retrieveFromAlias(alias).toResponse()

  fun create(request: ShortenedUrlCreateRequest, user:ShortenedUser): ShortenedUrlResponse =
    useCase.shorten(toDomain(request, user)).toResponse()

  fun deleteByAlias(alias: String) {
    useCase.deleteByAlias(alias)
  }

  private fun ShortenedUser.toDomain() = ShortenedUser(id, name)

  private fun ShortenedUrl.toResponse() =
    ShortenedUrlResponse(
      url = full, alias = alias, creationDateTime = creationDateTime.toString()
    )

  private fun List<ShortenedUrlResponse>.toListResponse() = ShortenedUrlListResponse(this)

  private fun toDomain(request: ShortenedUrlCreateRequest, user:ShortenedUser): ShortenedUrlCreation =
    ShortenedUrlCreation(
      alias = request.alias,
      full = request.url,
      owner = ShortenedUser(
        id = user.id,
        name = user.name,
      )
    )

}


