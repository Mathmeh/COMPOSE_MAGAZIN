package com.example.compose_magazin.DataAcces.mock

import com.example.compose_magazin.domain.entity.PetCategory
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.entity.PetTag

val mockCategories = listOf(
    PetCategory(
        1,
        "zubr"
    ),
    PetCategory(
        2,
        "busel"
    )
)

val mockTags = listOf(
    PetTag(
        1,
        "wild west"
    ),
    PetTag(
        2,
        "wild south"
    )
)
val mockUrl = listOf(
    "1",
    "2"
)
val mockPets = listOf(
    PetProduct(
        1,
        "vovan",
        mockCategories[1],
        mockTags,
        mockUrl,
        "df"
    ),
    PetProduct(
        2,
        "tolyan",
        mockCategories[1],
        mockTags,
        mockUrl,
        "df"
    )
)
