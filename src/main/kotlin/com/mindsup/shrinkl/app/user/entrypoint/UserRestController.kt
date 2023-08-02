package com.mindsup.shrinkl.app.user.entrypoint

import com.mindsup.shrinkl.app.user.entrypoint.payload.UserCreateRequest
import com.mindsup.shrinkl.app.user.entrypoint.payload.UserListResponse
import com.mindsup.shrinkl.app.user.entrypoint.payload.UserResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/user")
@RestController
class UserRestController (val userFacade: UserFacade) {

  @GetMapping
  fun getAll(): UserListResponse = this.userFacade.getAll()

  @GetMapping("/{id}")
  fun getById(@PathVariable id: String): UserResponse = this.userFacade.getById(id)

  @PostMapping
  fun create(@RequestBody request: UserCreateRequest): UserResponse = this.userFacade.create(request)

}
