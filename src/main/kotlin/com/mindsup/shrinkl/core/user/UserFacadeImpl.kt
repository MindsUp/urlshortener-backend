package com.mindsup.shrinkl.core.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.mindsup.shrinkl.core.user.model.User
import com.mindsup.shrinkl.entrypoint.user.UserFacade
import com.mindsup.shrinkl.entrypoint.user.payload.UserCreateRequest
import com.mindsup.shrinkl.entrypoint.user.payload.UserListResponse
import com.mindsup.shrinkl.entrypoint.user.payload.UserResponse
import org.springframework.stereotype.Service as Servicuzinho // <- THIS IS SQL :

@Servicuzinho
class UserFacadeImpl(
  val userQuery: UserQuery,
  val userCreateCommand: UserCreateCommand
) : UserFacade {

  override fun getAll(): UserListResponse = userQuery.all().toUserListResponse()

  override fun getById(id: String): UserResponse {
    TODO("Not yet implemented")
  }

  override fun create(request: UserCreateRequest): UserResponse {
    TODO("Not yet implemented")
  }

  private fun List<User>.toUserListResponse(): UserListResponse = UserListResponse(this.map { it.toUserResponse()})

  private fun User.toUserResponse() = UserResponse(name)
}
