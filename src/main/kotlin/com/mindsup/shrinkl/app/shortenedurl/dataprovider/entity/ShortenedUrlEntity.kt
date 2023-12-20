package com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document("shortened-url")
class ShortenedUrlEntity (
  @Id
  var id: String? = null,
  val owner: ShortenedUrlUserEntity,
  val createdAt: ZonedDateTime,
  val full: String,
  @Indexed(unique = true)
  val alias: String
)

data class ShortenedUrlUserEntity(
  val id: String,
  val name: String
)



