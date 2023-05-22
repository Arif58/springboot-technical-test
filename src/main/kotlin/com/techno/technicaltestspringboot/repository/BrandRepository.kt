package com.techno.technicaltestspringboot.repository

import com.techno.technicaltestspringboot.entity.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository: JpaRepository<BrandEntity, String> {
}