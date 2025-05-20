package com.example.application.user

import org.springframework.stereotype.Service
import com.example.domain.user.User
import com.example.domain.user.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()
}
