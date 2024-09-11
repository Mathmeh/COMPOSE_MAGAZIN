package com.example.compose_magazin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PetTagData(
    val id: Long,
    val name: String? = null
)
