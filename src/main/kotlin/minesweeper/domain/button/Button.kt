package minesweeper.domain.button

import minesweeper.domain.position.Position

sealed class Button(
    open val position: Position
)

class Mine(
    override val position: Position
) : Button(position) {

    companion object {
        fun of(row: Int, col: Int): Mine = Mine(Position(row, col))
    }
}

class NoMine(
    override val position: Position
) : Button(position) {

    companion object {
        fun of(row: Int, col: Int): NoMine = NoMine(Position(row, col))
    }
}
