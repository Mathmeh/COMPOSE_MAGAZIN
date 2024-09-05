package com.example.compose_magazin.dataAccess.models

import kotlinx.serialization.Serializable

@Serializable
data class PetProductData(
    val id: Long,
    val name: String? = "unknown",
    val category: PetCategoryData? = PetCategoryData(-1, ""),
    val tags: List<PetTagData>? = listOf(),
    val photoUrls: List<String?>? = listOf(),
    val status: String?
)
