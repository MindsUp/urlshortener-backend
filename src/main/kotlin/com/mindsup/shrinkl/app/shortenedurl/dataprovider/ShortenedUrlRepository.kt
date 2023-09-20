package com.mindsup.shrinkl.app.shortenedurl.dataprovider

import com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity.ShortenedUrlEntity
import com.mindsup.shrinkl.core.shortenedurl.dataprovider.ShortenedUrlDataProvider
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrl
import com.mindsup.shrinkl.core.shortenedurl.domain.ShortenedUrlCreation
import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
class ShortenedUrlRepository(
  val shortenedUrlJpa: ShortenedUrlJpa
): ShortenedUrlDataProvider {

  override fun save(shortenedUrlCreation: ShortenedUrlCreation): ShortenedUrl {
    val shortenedUrlEntity = ShortenedUrlEntity(
      owner = shortenedUrlCreation.owner,
      createdAt = ZonedDateTime.now(),
      full = shortenedUrlCreation.full,
      alias = shortenedUrlCreation.alias
    )
    val savedEntity = shortenedUrlJpa.save(shortenedUrlEntity)
    return savedEntity.toDomain()
  }

  override fun findAllByUser(user: User): List<ShortenedUrl> {
    val userShortenedUrlEntities = shortenedUrlJpa.findByOwnerId(user.id)
    return userShortenedUrlEntities.map { it.toDomain() }
  }

  override fun findByAlias(alias: String): ShortenedUrl {
    val shortenedUrlEntity = shortenedUrlJpa.findByAlias(alias)
    return shortenedUrlEntity.toDomain()
  }

  private fun ShortenedUrlEntity.toDomain(): ShortenedUrl {
    return ShortenedUrl(
      owner = this.owner,
      creationDateTime = this.createdAt.toLocalDateTime(),
      full = this.full,
      alias = this.alias
    )
  }

}
