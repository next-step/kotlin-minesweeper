package minesweeper.position

import minesweeper.board.BoardElement

data class Position(
    val col: Int,
    val row: Int
) {
    constructor(col: String, row: String): this(col.toInt(), row.toInt())
    operator fun plus(other: Position) = Position(this.row + other.row, this.col + other.col)

    fun nearPositions(boardElement: BoardElement): List<Position> =
        NEAR_POSITIONS.map { this + it }
            .filter { !boardElement.isOutOfRange(it) }

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
