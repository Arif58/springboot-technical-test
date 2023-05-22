package com.techno.technicaltestspringboot.service

import com.techno.technicaltestspringboot.dto.request.ReqGenerateToken
import com.techno.technicaltestspringboot.dto.response.BaseResponse
import com.techno.technicaltestspringboot.dto.response.ResGenerateToken
import com.techno.technicaltestspringboot.exception.CustomExceptionHandler
import com.techno.technicaltestspringboot.exception.ErrorHandler
import com.techno.technicaltestspringboot.repository.UserRepository
import com.techno.technicaltestspringboot.util.JWTGenerator
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.Exception
import java.lang.RuntimeException
import javax.crypto.Cipher.SECRET_KEY

@Service
class UserServiceImpl (
    val userRepository: UserRepository
        ) : UserService {
//    override fun loadUserByUsername(username: String?): UserDetails {
//        val user = userRepository.findByUsername(username!!)
//        if (user != null) {
//            return User(user.username, user.password, emptyList())
//        }
//        throw UsernameNotFoundException("User not found")
//    }
    override fun generateToken(username: String, password: String): ResGenerateToken? {
        val user = userRepository.findByUsername(username)
        if (user != null && user!!.password.equals(password)){
            val token = JWTGenerator().createJWT(user!!.id.toString(),user!!.name.toString(),user!!.username.toString(),user!!.email.toString(),user!!.id.toString())
            val claim = JWTGenerator().decodeJWT(token)
            return ResGenerateToken(
                token,
                "bearer",
                claim.expiration.time,
                "write, read"
            )
        } else {
            throw BadCredentialsException("Invalid username or password")
        }
    }
}