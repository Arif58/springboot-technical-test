package com.techno.technicaltestspringboot.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_example")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String? = "",
    val username: String? = "",
    val email: String? = "",
    val password: String? = ""
)
