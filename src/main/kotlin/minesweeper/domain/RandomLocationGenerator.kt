package minesweeper.domain

import minesweeper.dto.RandomLocation

object RandomLocationGenerator {
    fun location(size: Int, rows: Int, cols: Int): List<RandomLocation> {
        return generateSequence {
            val row = (0 until rows).random()
            val col = (0 until cols).random()
            RandomLocation(row, col)
        }
            .distinct()
            .take(size)
            .toList()
    }
}
