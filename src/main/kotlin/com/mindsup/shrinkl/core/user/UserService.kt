package com.mindsup.shrinkl.core.user

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.entrypoint.user.payload.UserCreateRequest
import com.mindsup.shrinkl.entrypoint.user.payload.UserResponse
import org.springframework.stereotype.Service

@Service
class UserService(
  val repository: UserRepository
) {

  fun getAll(): List<User> = repository.getAll()

  fun getById(id: String): UserResponse {
    TODO("Not yet implemented")
  }

   fun create(request: UserCreateRequest): UserResponse {
    TODO("Not yet implemented")
  }
}
