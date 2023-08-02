package com.mindsup.shrinkl.app.user.dataprovider

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.app.user.dataprovider.entity.UserEntity
import com.mindsup.shrinkl.core.user.dataprovider.UserDataProvider
import com.mindsup.shrinkl.core.user.domain.UserCreation
import org.springframework.stereotype.Repository

@Repository
class UserRepository(val userJpa: UserJpa) : UserDataProvider {

  override fun create(user: UserCreation) = userJpa.save(user.toEntity()).toDomain()
  override fun findAll() = userJpa.findAll().map { it.toDomain() }
  override fun findById(id: String) = userJpa.findById(id)
    .orElseThrow { IllegalArgumentException("User not Found") }.toDomain()

  private fun UserEntity.toDomain() = id?.let { User(it, name) } ?:
    throw IllegalArgumentException("Id not populated from DB")
  private fun UserCreation.toEntity() = UserEntity(name = name)

}

