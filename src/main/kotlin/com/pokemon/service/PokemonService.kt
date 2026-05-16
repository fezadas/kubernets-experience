package com.pokemon.service

import com.pokemon.model.PokemonApiResponse
import com.pokemon.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

object PokemonService {
    private const val API_BASE = "https://pokeapi.co/api/v2"
    private const val MAX_POKEMON = 1025

    private val json = Json { ignoreUnknownKeys = true }
    private val client = HttpClient(CIO)

    suspend fun getRandomPokemon(): PokemonResponse {
        val id = (1..MAX_POKEMON).random()
        return getPokemonById(id)
    }

    suspend fun getPokemonById(id: Int): PokemonResponse {
        val response = client.get("$API_BASE/pokemon/$id")
        val apiPokemon = json.decodeFromString<PokemonApiResponse>(response.bodyAsText())
        return apiPokemon.toResponse()
    }

    private fun PokemonApiResponse.toResponse() = PokemonResponse(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = types.map { it.type.name },
        sprite = sprites.frontDefault,
        height = height,
        weight = weight
    )
}
