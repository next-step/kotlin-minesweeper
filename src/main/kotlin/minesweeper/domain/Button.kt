package minesweeper.domain

import minesweeper.domain.position.Position

sealed class Button(
    open val position: Position
)

class Mine(
    override val position: Position
) : Button(position) {

    override fun toString(): String {
        return MINE_STRING
    }

    companion object {
        const val MINE_STRING = "*"

        fun of(row: Int, col: Int): Mine = Mine(Position(row, col))
    }
}

class NoMine(
    override val position: Position
) : Button(position) {

    override fun toString(): String {
        return NO_MINE_STRING
    }

    companion object {
        const val NO_MINE_STRING = "C"

        fun of(row: Int, col: Int): NoMine = NoMine(Position(row, col))
    }
}
