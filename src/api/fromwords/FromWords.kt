package me.akherbouch.wsg.api.main

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import me.akherbouch.wsg.API_VERSION
import me.akherbouch.wsg.core.PuzzleGenerator
import me.akherbouch.wsg.util.Language
import me.akherbouch.wsg.util.RandomWordsGenerator

const val FROM_WORDS_ENDPOINT = "$API_VERSION/fromwords"


data class RequestBody constructor(
    val rows : Int = 10,
    val cols : Int = 10,
    val words : List<String> = listOf()
)

fun Route.fromWords() {
    post(FROM_WORDS_ENDPOINT) {

        val body = call.receive<RequestBody>()

        val lang = call.request.queryParameters["lang"] ?: "en"
        val lg = Language.fromString(lang)

        val words = body.words.toMutableList()

        // adding some random words if num param is bigger than number of words
        call.request.queryParameters["num"]?.let {
            val d = it.toInt() - body.words.size
            if(d > 0) {
                val randomWords = RandomWordsGenerator.getWords(d, lg)
                words.addAll(randomWords)
            }
        }

        val puzzle = PuzzleGenerator.generate(body.rows, body.cols, words, lg.abc)
        call.respond(puzzle)
    }
}