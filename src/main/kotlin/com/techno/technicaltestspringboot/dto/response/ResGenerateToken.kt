package com.techno.technicaltestspringboot.dto.response

data class ResGenerateToken(
    val access_token: String?,
    val token_type: String?,
    val expires_in: Long?,
    val scope: String?
)
