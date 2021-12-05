package minesweeper.domain.position

import minesweeper.domain.board.BoardSettings

data class Position(val row: Int, val column: Int) : Comparable<Position> {

    override fun compareTo(other: Position): Int = compareValuesBy(this, other, { it.row }, { it.column })

    companion object {
        fun from(index: Int, settings: BoardSettings): Position {
            return Position(index / settings.width, index % settings.height)
        }
    }
}
