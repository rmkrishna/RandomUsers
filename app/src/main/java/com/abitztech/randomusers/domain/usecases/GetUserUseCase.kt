package com.abitztech.randomusers.domain.usecases

import com.abitztech.randomusers.domain.respository.UsersRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String) = usersRepository.getUser(id)
}