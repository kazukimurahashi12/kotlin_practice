package com.example.application.user

import com.example.domain.user.User
import com.example.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    // TODO
    @Transactional
    fun toggleActive(userID: Int) {
        val user =
                userRepository.findByID(userID)
                        ?: throw IllegalArgumentException("User not found: $userID")
        val updated = user.copy(isActive = !user.isActive)
        userRepository.update(updated)
    }

    @Transactional
    fun updateName(userID: Int, newName: String) {
        val user =
                userRepository.findByID(userID)
                        ?: throw IllegalArgumentException("User not found: $userID")
        val updated = user.updateNameIfActive(newName)
        userRepository.update(updated)
    }
}
