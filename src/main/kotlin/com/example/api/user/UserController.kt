package com.example.api.user

import com.example.application.user.UserService
import com.example.domain.user.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAll() = userService.getAllUsers()

    @PostMapping("/{id}/toggle-active")
    fun toggleActive(@PathVariable id: Int) {
        userService.toggleActive(id)
    }

    @PutMapping("/{id}")
    fun updateName(@PathVariable id: Int, @RequestBody body:  UpdateUserRequest) {
        userService.updateName(id,body.name)
    }
}