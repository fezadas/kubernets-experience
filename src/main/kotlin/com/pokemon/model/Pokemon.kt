package com.pokemon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<String>,
    val sprite: String?,
    val height: Int,
    val weight: Int
)

@Serializable
data class PokemonApiResponse(
    val id: Int,
    val name: String,
    val types: List<TypeSlot>,
    val sprites: Sprites,
    val height: Int,
    val weight: Int
)

@Serializable
data class TypeSlot(
    @SerialName("type") val type: TypeName
)

@Serializable
data class TypeName(
    val name: String
)

@Serializable
data class Sprites(
    @SerialName("front_default") val frontDefault: String?
)
