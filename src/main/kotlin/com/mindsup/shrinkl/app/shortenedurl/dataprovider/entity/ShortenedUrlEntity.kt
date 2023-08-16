package com.mindsup.shrinkl.app.shortenedurl.dataprovider.entity

import com.mindsup.shrinkl.core.user.domain.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document("shortened-url")
class ShortenedUrlEntity (
  @Id
  var id: String? = null,
  val owner: User,
  val createdAt: ZonedDateTime,
  val full: String,
  val alias: String
)
