package com.mindsup.shrinkl.core.shortenedurl.usecase

import com.mindsup.shrinkl.core.shortenedurl.dataprovider.ShortenedUrlDataProvider
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrlCreation
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUser
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.stereotype.Service

interface ShortenedUrlUseCase {
  fun shorten(shortenedUrlCreation: ShortenedUrlCreation): ShortenedUrl
  fun retrieveAllFromUser(user: ShortenedUser): List<ShortenedUrl>
  fun retrieveFromAlias(alias: String): ShortenedUrl
}

@Service
private class UrlShortenerService (
  val shortenedUrlDataProvider: ShortenedUrlDataProvider
): ShortenedUrlUseCase {

  override fun shorten(shortenedUrlCreation: ShortenedUrlCreation): ShortenedUrl =
    shortenedUrlDataProvider.save(shortenedUrlCreation)

  override fun retrieveAllFromUser(user: ShortenedUser): List<ShortenedUrl> =
    shortenedUrlDataProvider.findAllByUser(user)

  override fun retrieveFromAlias(alias: String): ShortenedUrl =
    shortenedUrlDataProvider.findByAlias(alias)

}
