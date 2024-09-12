package com.example.compose_magazin.data

import com.example.compose_magazin.data.api.RetrofitClient
import com.example.compose_magazin.data.mappers.petProductDataToPetProduct
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.PetRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl @Inject constructor() : PetRepository {
    override suspend fun getAvailablePets(): List<PetProduct> {
        val pets = RetrofitClient.petsProductApiClient.getAllPetsByStatus("available")
        return pets.map { petProductDataToPetProduct(it) }
    }
}
