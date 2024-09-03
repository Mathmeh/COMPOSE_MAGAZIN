package com.example.compose_magazin.domain.models

import com.example.compose_magazin.domain.models.Entity.PetProduct

interface PetRepository {
    suspend fun getAvaliablePets(): List<PetProduct>
}
