package com.mindsup.shrinkl.app.user.entrypoint.payload

import java.time.ZonedDateTime

data class UserResponse(
  val id: String,
  val name: String,
  val createdAt: ZonedDateTime
)
