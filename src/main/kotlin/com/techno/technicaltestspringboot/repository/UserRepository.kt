package com.techno.technicaltestspringboot.repository

import com.techno.technicaltestspringboot.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByUsername(username: String): UserEntity?
}