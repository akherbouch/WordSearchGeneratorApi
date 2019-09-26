package me.akherbouch.wsg.api.random

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import me.akherbouch.wsg.API_VERSION
import me.akherbouch.wsg.core.PuzzleGenerator
import me.akherbouch.wsg.util.Language
import me.akherbouch.wsg.util.RandomWordsGenerator
import java.lang.Integer.max

const val RANDOM_ENDPOINT = "$API_VERSION/random"

fun Route.random() {
    get(RANDOM_ENDPOINT) {
        val lang = call.request.queryParameters["lang"] ?: "en"
        val num = call.request.queryParameters["num"]?.toInt() ?: 10
        val lg = Language.fromString(lang)
        val randomWords = RandomWordsGenerator.getWords(num, lg)
        var maxLength = 0
        randomWords.forEach { maxLength = max(maxLength,it.length)}
        val puzzle = PuzzleGenerator.generate(num, maxLength, randomWords, lg.abc)
        call.respond(puzzle)
    }
}