package com.mindsup.shrinkl.entrypoint.user

import com.mindsup.shrinkl.entrypoint.user.payload.UserCreateRequest
import com.mindsup.shrinkl.entrypoint.user.payload.UserListResponse
import com.mindsup.shrinkl.entrypoint.user.payload.UserResponse

interface UserFacade {

  fun getAll(): UserListResponse
  fun getById(id:String): UserResponse
  fun create(request:UserCreateRequest): UserResponse
}
