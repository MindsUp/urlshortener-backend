package com.mindsup.shrinkl.core.user.dataprovider

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.core.user.domain.UserCreation

interface UserDataProvider {
  fun create(user: UserCreation): User
  fun findAll(): List<User>
  fun findById(id: String): User
}
