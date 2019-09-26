package me.akherbouch.wsg.core

import me.akherbouch.wsg.model.Direction
import me.akherbouch.wsg.model.Line
import me.akherbouch.wsg.model.WordLine

class Grid (
        private val rows : Int,
        private val cols : Int
) {

    private val _letters = Array(rows) { Array(cols) { "." } }

    val letters : List<String>
        get() = _letters.asList().flatMap { it.asList() }

    fun putWordLine(wordLine: WordLine) {
       with(wordLine) {
           for (i in 0 until line.length) {
               val r = line.row + line.dir.row*i
               val c = line.col + line.dir.col*i
               val letter = word.getLetter(i,isReversed)
               _letters[r][c] = letter
           }
       }
    }

    fun clear() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                _letters[i][j] = "."
            }
        }
    }

    fun getUnusedLines(length : Int): List<Line> {
        val lineSpaces = mutableListOf<Line>()
        val range = 0 until length
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (j <= cols - length && range.all { _letters[i][j+it] == "." })
                    lineSpaces += Line(i, j, length, Direction.HORIZONTAL)
                if (i <= rows - length) {
                    if (range.all { _letters[i+it][j] == "." })
                        lineSpaces += Line(i, j, length, Direction.VERTICAL)
                    if (j <= cols - length && range.all { _letters[i+it][j+it] == "." })
                        lineSpaces += Line(i, j, length, Direction.DIAGONAL)
                    if (j >= length - 1 && range.all { _letters[i+it][j-it] == "." })
                        lineSpaces += Line(i, j, length, Direction.ANTI_DIAGONAL)
                }
            }
        }
        return lineSpaces
    }

    fun fillEmptyCases(abc : List<String>) {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if(_letters[i][j] == ".") {
                    _letters[i][j] = abc.random() ?: ""
                }
            }
        }
    }

    override fun toString(): String = buildString {
        for (i in 0 until rows) {
            val col = _letters[i].joinToString("  ")
            appendln(col)
        }
    }

}




