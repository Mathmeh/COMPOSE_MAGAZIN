package com.example.compose_magazin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PetCategoryData(
    val id: Long,
    val name: String? = ""
)
