package com.example.compose_magazin.data.mappers

import com.example.compose_magazin.data.models.PetCategoryNetworkEntity
import com.example.compose_magazin.data.models.PetProductNetworkEntity
import com.example.compose_magazin.data.models.PetTagNetworkEntity
import com.example.compose_magazin.domain.entity.PetCategory
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.entity.PetTag

fun petProductDataToPetProduct(data: PetProductNetworkEntity): PetProduct =
    PetProduct(
        id = data.id,
        name = data.name,
        category = data.category?.let { petCategoryDataToPetCategory(it) },
        tags = data.tags?.map { petTagDataToPetTag(it) },
        photoUrls = data.photoUrls,
        status = data.status
    )

fun petTagDataToPetTag(data: PetTagNetworkEntity): PetTag =
    PetTag(
        id = data.id,
        name = data.name
    )

fun petCategoryDataToPetCategory(data: PetCategoryNetworkEntity): PetCategory =
    PetCategory(
        id = data.id,
        name = data.name
    )
