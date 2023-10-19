package com.mindsup.shrinkl.core.user.domain

import java.time.ZonedDateTime

data class User(
  val id: String,
  val name: String,
  val createdAt: ZonedDateTime
)

data class UserCreation(
  val name: String
)
