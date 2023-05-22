package com.techno.technicaltestspringboot.controller

import com.techno.technicaltestspringboot.dto.request.ReqFilterUnitBrand
import com.techno.technicaltestspringboot.dto.response.BaseResponse
import com.techno.technicaltestspringboot.dto.response.ResBrandFilter
import com.techno.technicaltestspringboot.service.BrandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("restv2/publicservices")
class BrandController (
    val brandService: BrandService
        ) {
    @PostMapping("/getbrand")
    fun getAll(@Valid @RequestBody request: ReqFilterUnitBrand): ResponseEntity<BaseResponse<List<ResBrandFilter>>>{
        val result = brandService.brandGetAll()
        return ResponseEntity.ok(
            BaseResponse(
                status = "T",
                message = "Success",
                data = result
            )
        )
    }
}