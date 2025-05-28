package com.example.domain.user

data class User(
    val id: Int,
    val name: String,
    val isActive: Boolean
) {
    fun updateNameIfActive(newName: String): User {
        if (!isActive) throw IllegalStateException("User is inactive")
        return this.copy(name = newName)
    }
}