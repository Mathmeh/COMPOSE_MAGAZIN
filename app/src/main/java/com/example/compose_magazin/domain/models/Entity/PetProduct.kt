package com.example.compose_magazin.domain.models.Entity

data class PetProduct(
    val id: Int,
    val name: String,
    val category: PetCategory,
    val tags: List<PetTag>,
    val photoUrls: List<String>,
    val status: String
)
