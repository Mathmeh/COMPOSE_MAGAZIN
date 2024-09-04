package com.example.compose_magazin.DataAcces.models

import kotlinx.serialization.Serializable

@Serializable
data class PetProductData(
    val id: Long,
    val name: String?,
    val category: PetCategoryData? = PetCategoryData(-1, ""),
    val tags: List<PetTagData>?,
    val photoUrls: List<String>?,
    val status: String?
)
