package minesweeper.domain

object RandomLocationGenerator {
    fun location(size: Int, rows: Int, cols: Int): List<Pair<Int, Int>> {
        return generateSequence {
            val raw = (0 until rows).random()
            val col = (0 until cols).random()
            Pair(raw, col)
        }
            .distinct()
            .take(size)
            .toList()
    }
}
