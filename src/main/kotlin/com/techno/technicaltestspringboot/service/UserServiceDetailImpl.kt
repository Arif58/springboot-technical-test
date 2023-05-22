package com.techno.technicaltestspringboot.service

import com.techno.technicaltestspringboot.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceDetailImpl(val userRepository: UserRepository

) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username!!)
        if (user != null) {
            return User(user.username, user.password, emptyList())
        }
        throw UsernameNotFoundException("User not found")
    }

}