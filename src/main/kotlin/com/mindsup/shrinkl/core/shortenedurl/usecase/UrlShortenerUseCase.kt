package com.mindsup.shrinkl.core.shortenedurl.usecase

import com.mindsup.shrinkl.core.shortenedurl.dataprovider.ShortenedUrlDataProvider
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.stereotype.Service

interface ShortenedUrlUseCase {
  fun shorten(shortenedUrl: ShortenedUrl)
  fun retrieveAllFromUser(user: User): List<ShortenedUrl>
  fun retrieveFromAlias(alias: String): ShortenedUrl
}

@Service
private class UrlShortenerService (
  val shortenedUrlDataProvider: ShortenedUrlDataProvider
): ShortenedUrlUseCase {

  override fun shorten(shortenedUrl: ShortenedUrl) {
    TODO("Not yet implemented")
  }

  override fun retrieveAllFromUser(user: User): List<ShortenedUrl> {
    TODO("Not yet implemented")
  }

  override fun retrieveFromAlias(alias: String): ShortenedUrl {
    TODO("Not yet implemented")
  }

}
