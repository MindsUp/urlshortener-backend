package com.mindsup.shrinkl.app.shortenedurl.entrypoint.payload

data class ShortenedUrlCreateRequest (
  val url: String,
  val alias: String
)
