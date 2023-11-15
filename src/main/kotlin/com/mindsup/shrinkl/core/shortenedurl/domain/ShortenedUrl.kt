package com.mindsup.shrinkl.core.shortenedurl.domain

import java.time.LocalDateTime

data class ShortenedUrl (
  val owner: ShortenedUser,
  val createdAt: LocalDateTime,
  val full: String,
  val alias: String
)

data class ShortenedUrlCreation (
  val owner: ShortenedUser,
  val full: String,
  val alias: String
)

data class ShortenedUser(
  val id: String,
  val name: String
)
