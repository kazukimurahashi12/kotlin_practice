package com.example.application.user

import org.springframework.stereotype.Service
import com.example.domain.user.User
import com.example.domain.user.UserRepository
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    @Transactional
    fun toggleActive(userID: Int) {
        val user = userRepository.findByID(userID)
            ?: throw IllegalArgumentException("User not found: $userID")
        val updated = user.copy(isActive = !user.isActive)
        userRepository.update(updated)
    }
}
