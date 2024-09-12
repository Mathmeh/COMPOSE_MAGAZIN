package com.example.compose_magazin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PetCategoryNetworkEntity(
    val id: Long,
    val name: String? = ""
)
