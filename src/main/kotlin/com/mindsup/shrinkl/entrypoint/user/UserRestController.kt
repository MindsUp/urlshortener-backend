package com.mindsup.shrinkl.entrypoint.user

import com.mindsup.shrinkl.entrypoint.user.payload.UserCreateRequest
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/user")
@RestController
class UserRestController (val userFacade: UserFacade) {

  @GetMapping()
  fun getAll() = this.userFacade.getAll()

  @GetMapping("/{id}")
  fun getById(@PathVariable id: String) = this.userFacade.getById(id)

  @PostMapping()
  fun create(@RequestBody request: UserCreateRequest) = this.userFacade.create(request)

}
