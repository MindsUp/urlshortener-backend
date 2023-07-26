package com.mindsup.shrinkl.gateway.user

import com.mindsup.shrinkl.gateway.user.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserJpa: CrudRepository<UserEntity, String> {


}
