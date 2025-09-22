package com.example.infrastructure.user

import com.example.domain.user.User
import com.example.domain.user.UserRepository
import com.example.jooq.generated.tables.Users.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val dsl: DSLContext): UserRepository {
    // 全件検索
     override fun findAll(): List<User> {
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

    // ID検索
    override fun findByID(id: Int):User? {
        return dsl.selectFrom(USERS)
            .where(USERS.ID.eq(id))
            .fetchOne()
            ?.let { User(it.id, it.name, it.isActive ?: false) }
    }

    // 更新
    override fun update(user : User) {
        dsl.update(USERS)
            .set(USERS.NAME, user.name)
            .set(USERS.IS_ACTIVE, user.isActive)
            .where(USERS.ID.eq(user.id))
            .execute()
    }
}