package com.mindsup.shrinkl.app.user.dataprovider.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document("user")
class UserEntity (
  @Id
  var id: String? = null,
  var name: String,
  @CreatedDate
  var createdAt: String
)
