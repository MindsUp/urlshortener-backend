package com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload

data class ShortenedUrlResponse (
  val url: String,
  val alias: String,
  val creationDateTime: String
)

