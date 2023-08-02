package com.mindsup.shrinkl.app.user.dataprovider.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
class UserEntity (
  @Id
  var id: String? = null,
  var name: String
)
