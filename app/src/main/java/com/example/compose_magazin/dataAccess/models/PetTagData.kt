package com.example.compose_magazin.dataAccess.models

import kotlinx.serialization.Serializable

@Serializable
data class PetTagData(
    val id: Long,
    val name: String? = null
)
