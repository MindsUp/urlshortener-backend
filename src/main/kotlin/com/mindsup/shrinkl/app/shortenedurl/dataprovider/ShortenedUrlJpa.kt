package com.mindsup.shrinkl.app.shortenedurl.dataprovider

import com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity.ShortenedUrlEntity
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.data.repository.CrudRepository

interface ShortenedUrlJpa: CrudRepository<ShortenedUrlEntity, String> {
  fun findByOwnerId(userId: String): List<ShortenedUrlEntity>
  fun findByAlias(alias: String): ShortenedUrlEntity
}
