package com.pokemon.route

import com.pokemon.service.PokemonService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.pokemonRoutes() {
    get("/") {
        val html = """
            <!DOCTYPE html>
            <html>
            <head><title>Pokémon API</title>
            <style>
                body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background: #1a1a2e; color: #eee; margin: 0; }
                .card { text-align: center; padding: 2rem; background: #16213e; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.3); }
                a { color: #e94560; text-decoration: none; font-weight: bold; }
                a:hover { text-decoration: underline; }
            </style>
            </head>
            <body>
                <div class="card">
                    <h1>Pokémon API</h1>
                    <p><a href="/api/pokemon/random">/api/pokemon/random</a> — Get a random Pokémon</p>
                    <p><a href="/api/pokemon/health">/api/pokemon/health</a> — Health check</p>
                    <p><a href="/metrics">/metrics</a> — Prometheus metrics</p>
                </div>
            </body>
            </html>
        """.trimIndent()
        call.respond(HttpStatusCode.OK, html)
    }

    get("/api/pokemon/random") {
        val pokemon = PokemonService.getRandomPokemon()
        call.respond(pokemon)
    }

    get("/api/pokemon/health") {
        call.respond(mapOf("status" to "ok"))
    }
}
