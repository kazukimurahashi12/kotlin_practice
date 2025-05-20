package com.example.infrastructure.user

import com.example.domain.user.User
import com.example.jooq.generated.tables.Users.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val dsl: DSLContext) {
    fun findAll(): List<User> {
        return dsl.selectFrom(USERS)
            .fetch()
            .map {
                User(
                    id = it.id,
                    name = it.name,
                    isActive = it.isActive ?: false
                )
            }
    }
}