package com.example.compose_magazin.domain

import com.example.compose_magazin.domain.entity.PetProduct

interface PetRepository {
    suspend fun getAvailablePets(): List<PetProduct>
    suspend fun getPetById(id: Long): PetProduct
}
