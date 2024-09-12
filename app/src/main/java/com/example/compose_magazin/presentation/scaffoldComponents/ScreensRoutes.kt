package com.example.compose_magazin.presentation.scaffoldComponents

import kotlinx.serialization.Serializable

@Serializable
object Catalog

@Serializable
object Parent

@Serializable
object Cart

@Serializable
object Settings

@Serializable
data class AboutProductData(val id: Long)
