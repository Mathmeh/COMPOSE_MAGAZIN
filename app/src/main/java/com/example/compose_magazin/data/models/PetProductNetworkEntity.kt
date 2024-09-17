package com.example.compose_magazin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PetProductNetworkEntity(
    val id: Long,
    val name: String? = "unknown",
    val category: PetCategoryNetworkEntity? = PetCategoryNetworkEntity(-1, ""),
    val tags: List<PetTagNetworkEntity>?,
    val photoUrls: List<String>?,
    val status: String?
)
