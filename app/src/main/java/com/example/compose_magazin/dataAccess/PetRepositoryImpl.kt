package com.example.compose_magazin.dataAccess

import com.example.compose_magazin.dataAccess.api.RetrofitClient
import com.example.compose_magazin.dataAccess.mappers.petProductDataToPetProduct
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.PetRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl
@Inject constructor() : PetRepository {
    override suspend fun getAvailablePets(): List<PetProduct> {
        val pets = RetrofitClient.apiClient.getAllPetsByStatus("available")
        return pets.map { petProductDataToPetProduct(it) }
    }

    override suspend fun getPetById(id: Long): PetProduct {
        val pet = RetrofitClient.apiClient.getPetById(id)
        return petProductDataToPetProduct(pet)
    }
}
