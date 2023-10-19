package com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document("shortened-url")
class ShortenedUrlEntity (
  @Id
  var id: String? = null,
  val owner: ShortenedUrlUserEntity,
  val createdAt: ZonedDateTime,
  val full: String,
  val alias: String
)

data class ShortenedUrlUserEntity(
  val id: String,
  val name: String
)



