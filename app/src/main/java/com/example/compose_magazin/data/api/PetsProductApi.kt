package com.example.compose_magazin.data.api

import com.example.compose_magazin.data.models.PetProductNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PetsProductApi {
    @GET("pet/findByStatus")
    suspend fun getAllPetsByStatus(
        @Query("status")
        status: String
    ): List<PetProductNetworkEntity>
}
