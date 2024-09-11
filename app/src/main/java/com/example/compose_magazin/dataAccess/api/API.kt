package com.example.compose_magazin.dataAccess.api

import com.example.compose_magazin.dataAccess.models.PetProductData
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("pet/findByStatus")
    suspend fun getAllPetsByStatus(
        @Query("status") status: String
    ): List<PetProductData>
}
