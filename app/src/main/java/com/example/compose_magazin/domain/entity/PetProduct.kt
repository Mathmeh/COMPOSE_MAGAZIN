package com.example.compose_magazin.domain.entity

data class PetProduct(
    val id: Long,
    val name: String?,
    val category: PetCategory?,
    val tags: List<PetTag>?,
    val photoUrls: List<String?>?,
    val status: String?
)
