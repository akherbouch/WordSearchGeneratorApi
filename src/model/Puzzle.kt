package me.akherbouch.wsg.model


data class Puzzle (
        val rows : Int,
        val cols : Int,
        val letters : List<String>,
        val wordLines: List<WordLine>
)