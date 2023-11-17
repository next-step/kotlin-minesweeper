package minesweeper.domain

object MineGenerator {

    fun generate(positions: List<Position>, mineCount: Int): Mines {
        val copyPosition = positions.map { it.copy() }
        val mines = copyPosition
            .map { Mine(it) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }
}
