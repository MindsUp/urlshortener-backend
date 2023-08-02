package com.mindsup.shrinkl.app.user.dataprovider

import com.mindsup.shrinkl.app.user.dataprovider.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserJpa: CrudRepository<UserEntity, String>
