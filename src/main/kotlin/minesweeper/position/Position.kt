package minesweeper.position

import minesweeper.board.BoardElement

data class Position(
    val col: Int,
    val row: Int
) {
    operator fun plus(other: Position) = Position(this.row + other.row, this.col + other.col)

    fun nearPositions(boardElement: BoardElement): List<Position> =
        NEAR_POSITIONS.map { this + it }
            .filter { it.isNotOutOfRange(boardElement) }

    private fun isNotOutOfRange(boardElement: BoardElement) =
        boardElement.height > this.row && this.row >= 0 && boardElement.width > this.col && this.col >= 0

    companion object {
        private val NEAR_POSITIONS = arrayOf(
            Position(0, -1),
            Position(1, -1),
            Position(1, 0),
            Position(1, 1),
            Position(0, 1),
            Position(-1, 1),
            Position(-1, 0),
            Position(-1, -1)
        )
    }
}
