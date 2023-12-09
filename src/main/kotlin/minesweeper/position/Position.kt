package minesweeper.position

import minesweeper.board.BoardDimensions

data class Position(
    val y: Int,
    val x: Int
) {
    operator fun plus(other: Position) = Position(this.y + other.y, this.x + other.x)

    fun nearPositions(boardDimensions: BoardDimensions): List<Position> =
        NEAR_POSITIONS.map { this + it }
            .filter { it.isNotOutOfRange(boardDimensions) }

    private fun isNotOutOfRange(boardDimensions: BoardDimensions) =
         boardDimensions.height > this.y && this.y >= 0 && boardDimensions.width > this.x && this.x >= 0
    companion object {
        private val NEAR_POSITIONS = arrayOf(
            Position(0 , -1),
            Position(1 , -1),
            Position(1 , 0),
            Position(1 , 1),
            Position(0 , 1),
            Position(-1 , 1),
            Position(-1 , 0),
            Position(-1 , -1)
        )
    }
}
