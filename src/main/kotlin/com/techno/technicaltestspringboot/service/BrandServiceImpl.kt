package com.techno.technicaltestspringboot.service

import com.techno.technicaltestspringboot.dto.request.ReqFilterUnitBrand
import com.techno.technicaltestspringboot.dto.response.ResBrandFilter
import com.techno.technicaltestspringboot.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    val brandRepository: BrandRepository
): BrandService {
    override fun brandGetAll(): List<ResBrandFilter> {
        val brand = brandRepository.findAll()
        val result = mutableListOf<ResBrandFilter>()
        for (i in brand){
            result.add(
                ResBrandFilter(
                    cd_brand = i.cd_brand,
                    desc_brand = i.desc_brand
                )
            )
        }
        return result
    }
}