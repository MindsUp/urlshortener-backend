package com.mindsup.shrinkl.gateway.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
class UserEntity (
  @Id
  var id: String,
  var name: String
)
