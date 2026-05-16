package com.pokemon

import com.pokemon.route.pokemonRoutes
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080

    embeddedServer(Netty, port = port) {
        routing {
            pokemonRoutes()
        }
    }.start(wait = true)
}
