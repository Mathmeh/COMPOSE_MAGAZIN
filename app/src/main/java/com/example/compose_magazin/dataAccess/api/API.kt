package com.example.compose_magazin.dataAccess.api

import com.example.compose_magazin.dataAccess.models.PetProductData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("pet/findByStatus")
    suspend fun getAllPetsByStatus(
        @Query("status") status: String
    ): List<PetProductData>
    @GET("pet/{id}")
    suspend fun getPetById(@Path("id") id: Long): PetProductData
}
