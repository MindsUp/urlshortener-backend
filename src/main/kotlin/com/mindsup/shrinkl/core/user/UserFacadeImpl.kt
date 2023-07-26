package com.mindsup.shrinkl.core.user

import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.entrypoint.user.UserFacade
import com.mindsup.shrinkl.entrypoint.user.payload.UserCreateRequest
import com.mindsup.shrinkl.entrypoint.user.payload.UserListResponse
import com.mindsup.shrinkl.entrypoint.user.payload.UserResponse
import org.springframework.stereotype.Service

@Service
class UserFacadeImpl(
  val userService: UserService
) : UserFacade {

  override fun getAll(): UserListResponse = userService.getAll().toUserListResponse()

  override fun getById(id: String): UserResponse {
    TODO("Not yet implemented")
  }

  override fun create(request: UserCreateRequest): UserResponse {
    TODO("Not yet implemented")
  }

  private fun List<User>.toUserListResponse(): UserListResponse = UserListResponse(this.map { it.toUserResponse()})

  private fun User.toUserResponse() = UserResponse(id, name)
}
