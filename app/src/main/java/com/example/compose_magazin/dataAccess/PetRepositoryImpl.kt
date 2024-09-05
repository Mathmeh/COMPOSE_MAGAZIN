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
        println("aaaa1")
        return pets.map { petProductDataToPetProduct(it) }
    }
}
