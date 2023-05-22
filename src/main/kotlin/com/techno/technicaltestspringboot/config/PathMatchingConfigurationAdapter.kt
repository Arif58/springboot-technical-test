package com.techno.technicaltestspringboot.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PathMatchingConfigurationAdapter(
    val grantInterceptor: GrantInterceptor,
    val bearerInterceptor: BearerInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(grantInterceptor).addPathPatterns("/apiservice/oauth/token")
        registry.addInterceptor(bearerInterceptor).addPathPatterns("/restv2/publicservices/getbrand")
    }
}