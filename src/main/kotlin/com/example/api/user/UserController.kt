package com.example.api.user

import com.example.application.user.UserService
import com.example.domain.user.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @PostMapping("/{id}/toggle-active")
    fun toggleActive(@PathVariable id: Int) {
        userService.toggleActive(id)
    }
}