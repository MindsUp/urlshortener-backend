package com.mindsup.shrinkl.core.user.usecase

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.core.user.domain.UserCreation

interface UserUseCase {
  fun create(user: UserCreation) : User
  fun getAll(): List<User>
  fun getById(id:String): User
}
