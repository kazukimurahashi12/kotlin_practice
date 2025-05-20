package com.example.domain.user

interface UserRepository {
    fun findAll(): List<User>
}