package com.techno.technicaltestspringboot.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestspringboot.exception.CustomExceptionHandler
import com.techno.technicaltestspringboot.util.JWTGenerator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BearerInterceptor(
    val jwtGenerator: JWTGenerator
) : HandlerInterceptor {
    val log = LoggerFactory.getLogger(this::class.java)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = extractTokenFromRequest(request)
        val tokenValidate = jwtGenerator.validateToken(token.toString())
//        val tokenValidate = jwtGenerator.decodeJWT(token.toString())
        log.info(tokenValidate.toString())
        if (token == null || tokenValidate == false) {
//            throw CustomExceptionHandler("Token Invalid")
            val json = ObjectMapper().writeValueAsString(
                mapOf(
                    "OUT_STAT" to "F",
                    "OUT_MESS" to "Signature Not Valid",
                    "OUT_DATA" to emptyList<String>()
                )
            )
            response.writer.write(json)
            response.contentType="application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true
    }

    private fun extractTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}