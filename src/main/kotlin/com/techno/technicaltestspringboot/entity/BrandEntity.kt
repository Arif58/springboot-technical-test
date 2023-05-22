package com.techno.technicaltestspringboot.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "brand")
data class BrandEntity(
    @Id
    val cd_brand: String? = "",
    val desc_brand: String? = ""
)
