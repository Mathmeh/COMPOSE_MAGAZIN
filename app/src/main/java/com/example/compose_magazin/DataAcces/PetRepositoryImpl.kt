package com.example.compose_magazin.DataAcces

import com.example.compose_magazin.DataAcces.api.RetrofitClient
import com.example.compose_magazin.DataAcces.mappers.petProductDataToPetProduct
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.PetRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl
@Inject constructor() : PetRepository {
    override suspend fun getAvaliablePets(): List<PetProduct> {
        val pets = RetrofitClient.apiClient.getAllPetsByStatus("available")
        return pets.map { petProductDataToPetProduct(it) }
    }
}
