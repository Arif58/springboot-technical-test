package com.techno.technicaltestspringboot.controller

import com.techno.technicaltestspringboot.dto.response.BaseResponse
import com.techno.technicaltestspringboot.dto.response.ResGenerateToken
import com.techno.technicaltestspringboot.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.naming.AuthenticationException
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("apiservice/oauth")
class UserController (
    val userService: UserService,
        ) {

    @PostMapping("/token")
    fun generateToken(@Valid request: HttpServletRequest) : ResponseEntity<BaseResponse<ResGenerateToken>> {
        try {
            val authorizationHeader = request.getHeader("Authorization")
            val base64Credentials = authorizationHeader.substring("Basic ".length)
            val credentials = String(Base64.getDecoder().decode(base64Credentials)).split(":")
            val username = credentials[0]
            val password = credentials[1]

            // Menghasilkan token JWT
            val result = userService.generateToken(username, password)

            return ResponseEntity.ok(
                BaseResponse(
                    status = "T",
                    message = "Success",
                    data = result
                )
            )

        } catch (e: AuthenticationException) {
            return ResponseEntity.ok(
                BaseResponse(
                    status = "F",
                    message = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password or email").toString(),
                    null
                )
            )
        }
    }
}