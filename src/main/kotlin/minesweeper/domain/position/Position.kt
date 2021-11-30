package minesweeper.domain.position

import minesweeper.domain.cell.Cells

data class Position private constructor(val x: Int, val y: Int) {

    lateinit var adjacentPositions: Positions
        private set

    fun updateAdjacentPositions(allPositions: Positions) {
        val value = CompassDirections.values()
            .map {
                of(x + it.x, y + it.y)
            }.filter {
                checkValidPosition(it.x, it.y) && it in allPositions
            }

        adjacentPositions = Positions.of(value)
    }

    fun containsAdjacentPositions(otherPosition: Position): Boolean =
        otherPosition in adjacentPositions

    fun countingAdjacentMines(mineCells: Cells): Int = this.adjacentPositions.count { it in mineCells.toPositions() }

    companion object {
        fun of(x: Int, y: Int): Position {
            require(checkValidPosition(x, y)) { X_AND_Y_GRATER_THEN_MINUS_ONE }
            return Position(x, y)
        }

        private fun checkValidPosition(x: Int, y: Int) = x > -1 && y > -1
        private const val X_AND_Y_GRATER_THEN_MINUS_ONE = "x와 y는 -1 보다 커야 합니다."
    }
}
