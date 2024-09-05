package com.example.compose_magazin.dataAccess.mappers

import com.example.compose_magazin.dataAccess.models.PetCategoryData
import com.example.compose_magazin.dataAccess.models.PetProductData
import com.example.compose_magazin.dataAccess.models.PetTagData
import com.example.compose_magazin.domain.entity.PetCategory
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.entity.PetTag

fun petProductDataToPetProduct(data: PetProductData): PetProduct =
    PetProduct(
        id = data.id,
        name = data.name,
        category = data.category?.let { petCategoryDataToPetCategory(it) },
        tags = data.tags?.map { petTagDataToPetTag(it) },
        photoUrls = data.photoUrls,
        status = data.status
    )

fun petTagDataToPetTag(data: PetTagData): PetTag =
    PetTag(
        id = data.id,
        name = data.name
    )

fun petCategoryDataToPetCategory(data: PetCategoryData): PetCategory =
    PetCategory(
        id = data.id,
        name = data.name
    )
