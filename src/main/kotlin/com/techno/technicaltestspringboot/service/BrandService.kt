package com.techno.technicaltestspringboot.service

import com.techno.technicaltestspringboot.dto.request.ReqFilterUnitBrand
import com.techno.technicaltestspringboot.dto.response.ResBrandFilter

interface BrandService {
//    fun brandGetAll(request: ReqFilterUnitBrand): List<ResBrandFilter>
    fun brandGetAll(): List<ResBrandFilter>

}