package com.techno.technicaltestspringboot.service

import com.techno.technicaltestspringboot.dto.request.ReqGenerateToken
import com.techno.technicaltestspringboot.dto.response.ResGenerateToken
import org.springframework.security.core.userdetails.UserDetails

interface UserService {
//    fun userSave(request: CrudUserDTO)
//    fun userGetById(id: Int) : CrudUserDTO
//    fun userGetAll(): List<CrudUserDTO>
//    fun userUpdate(id: Int, request: CrudUserDTO)
//    fun userDelete(id: Int)
//    fun loadUserByUsername(username: String?):UserDetails
    fun generateToken(username: String, password: String):ResGenerateToken?
//    fun validateLoginUser(token: String): ResValidateLogin?
}