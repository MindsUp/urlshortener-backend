package com.mindsup.shrinkl.core.user.usecase

import com.mindsup.shrinkl.core.user.dataprovider.UserDataProvider
import com.mindsup.shrinkl.core.user.domain.UserCreation
import org.springframework.stereotype.Service

@Service
class UserService(
  private val dataProvider: UserDataProvider
) : UserUseCase {
  override fun create(user: UserCreation) = dataProvider.create(user)
  override fun getAll() = dataProvider.findAll()
  override fun getById(id: String) = dataProvider.findById(id)
}
