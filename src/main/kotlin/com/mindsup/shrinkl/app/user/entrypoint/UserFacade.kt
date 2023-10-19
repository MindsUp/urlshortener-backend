package com.mindsup.shrinkl.app.user.entrypoint

import com.mindsup.shrinkl.app.user.entrypoint.payload.UserCreateRequest
import com.mindsup.shrinkl.app.user.entrypoint.payload.UserListResponse
import com.mindsup.shrinkl.app.user.entrypoint.payload.UserResponse
import com.mindsup.shrinkl.core.user.domain.User
import com.mindsup.shrinkl.core.user.domain.UserCreation
import com.mindsup.shrinkl.core.user.usecase.UserUseCase
import org.springframework.stereotype.Service

@Service
class UserFacade(
  val userUseCase: UserUseCase
){

  fun getAll() = UserListResponse(userUseCase.getAll().map { it.toPayload() })

  fun getById(id: String) = userUseCase.getById(id).toPayload()

  fun create(request: UserCreateRequest) = userUseCase.create(request.toDomain()).toPayload()

  private fun User.toPayload() = UserResponse(id, name, createdAt)
  private fun UserCreateRequest.toDomain() = UserCreation(name)
}
