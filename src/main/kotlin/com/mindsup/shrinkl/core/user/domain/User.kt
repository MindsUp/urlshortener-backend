package com.mindsup.shrinkl.core.user.domain

data class User(
  val id: String,
  val name: String
)

data class UserCreation(
  val name: String
)
