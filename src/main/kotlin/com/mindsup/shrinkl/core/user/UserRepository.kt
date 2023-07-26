package com.mindsup.shrinkl.core.user

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.gateway.user.UserJpa
import com.mindsup.shrinkl.gateway.user.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class UserRepository(val userJpa: UserJpa) {

  fun getAll(): List<User> = userJpa.findAll().map { it.toUser() }

  private fun UserEntity.toUser() = User(id, name)

}

