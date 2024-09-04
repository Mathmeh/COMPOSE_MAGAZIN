package com.example.compose_magazin.DataAcces.models

import kotlinx.serialization.Serializable

@Serializable
data class PetTagData(
    val id: Long,
    val name: String?
)
