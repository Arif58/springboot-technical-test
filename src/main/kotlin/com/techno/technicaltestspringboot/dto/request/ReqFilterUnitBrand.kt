package com.techno.technicaltestspringboot.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ReqFilterUnitBrand(
    @field:Valid
    val getListFilterUnitBrand: DescBrand
)

data class DescBrand(
    @JsonProperty("DESC_BRAND")
    @field:Pattern(regexp = "^[a-zA-Z0-9]*$", message = "DESC_BRAND must be alphanumeric")
    @field:Size(max = 10, message = "DESC_BRAND cant be more than 10 character")
    val DESC_BRAND: String?
)
