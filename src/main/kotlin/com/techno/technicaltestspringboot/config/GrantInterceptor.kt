package com.techno.technicaltestspringboot.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class GrantInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val grantType = request.getParameter("grant_type")
//        val valueGrant = grantRepository.findByGrantType(grantType)
//        val log = LoggerFactory.getLogger(this::class.java)
//        log.info(grantType)

        if (grantType == null || grantType !in listOf("client_credentials1", "client_credentials2", "client_credentials3")) {
            val result = mapOf<Any, Any>("status" to "F", "message" to "invalid_client", "data" to mapOf<String, String>("error_description" to "Client authenticated failed"))
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        super.postHandle(request, response, handler, modelAndView)
    }

    fun convertObjectToJson(dto : Map<Any, Any>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}