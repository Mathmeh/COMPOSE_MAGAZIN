package com.example.compose_magazin.di

import com.example.compose_magazin.domain.models.DataAcces.PetRepositoryImpl
import com.example.compose_magazin.domain.models.PetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindPetRepository(
        petRepositoryImpl: PetRepositoryImpl
    ): PetRepository
}
