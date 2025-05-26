package com.example.domain.user

interface UserRepository {
    fun findAll(): List<User>
    fun findByID(id: Int): User?
    fun update(user: User)
}