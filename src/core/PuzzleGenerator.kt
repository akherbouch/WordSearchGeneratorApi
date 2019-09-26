package me.akherbouch.wsg.core

import me.akherbouch.wsg.model.Puzzle
import me.akherbouch.wsg.model.WordLine
import java.util.*


object PuzzleGenerator {

    fun generate(rows : Int, cols : Int, unsortedWords : List<String>, abc : List<String>) : Puzzle {
        val words = unsortedWords.sortedByDescending { it.length }
        val grid = Grid(rows,cols)
        val wordLines = mutableListOf<WordLine>()

        var attempts = 0
        var min = words.size
        while (true) {
            grid.clear()
            wordLines.clear()
            for (word in words) {
                val isReversed = Random().nextBoolean()
                val line = grid.getUnusedLines(word.length).random()
                val wordLine = WordLine(line, word, isReversed)
                grid.putWordLine(wordLine)
                wordLines += wordLine
            }
            if(wordLines.size >= min) break
            attempts += 1
            min = words.size - attempts%100
        }

        grid.fillEmptyCases(abc)
        val letters = grid.letters

        return Puzzle(rows, cols, letters, wordLines)
    }

}



