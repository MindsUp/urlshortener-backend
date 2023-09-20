package com.mindsup.shrinkl.core.shortenedurl.dataprovider

import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrlCreation
import com.mindsup.shrinkl.core.user.domain.User

interface ShortenedUrlDataProvider {
  fun save(shortenedUrlCreation: ShortenedUrlCreation): ShortenedUrl
  fun findAllByUser(user: User): List<ShortenedUrl>
  fun findByAlias(alias: String): ShortenedUrl
}
