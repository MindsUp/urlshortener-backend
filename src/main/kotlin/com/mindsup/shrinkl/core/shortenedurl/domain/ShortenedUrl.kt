package com.mindsup.shrinkl.core.shortenedurl.domain

import com.mindsup.shrinkl.core.user.domain.User
import java.time.LocalDateTime

data class ShortenedUrl (
  val owner: User,
  val creationDateTime: LocalDateTime,
  val full: String,
  val alias: String
)

data class ShortenedUrlCreation (
  val owner: User,
  val full: String,
  val alias: String
)
