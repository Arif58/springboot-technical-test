package com.techno.technicaltestspringboot.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any>{
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to exception.message!!)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to errors)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<Any> {
        val result = mapOf<String, Any>("OUT_STAT" to "F", "OUT_MESS" to "invalid client", "OUT_DATA" to mapOf<String, String>("error_description" to "Client authenticated failed"))
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result)
    }
}