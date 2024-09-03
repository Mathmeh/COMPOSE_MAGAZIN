package com.example.compose_magazin.domain.models.DataAcces

import com.example.compose_magazin.domain.models.DataAcces.Mock.mockPets
import com.example.compose_magazin.domain.models.Entity.PetProduct
import com.example.compose_magazin.domain.models.PetRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl
@Inject constructor() : PetRepository {
    override suspend fun getAvaliablePets(): List<PetProduct> {
        return mockPets
    }
}
