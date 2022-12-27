package minesweeper.domain.button

import minesweeper.domain.position.Position

sealed class Button(
    val position: Position
) {
    val row: Int = position.row
    val col: Int = position.col

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Button

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }
}

class Mine(
    position: Position
) : Button(position) {

    companion object {
        fun of(row: Int, col: Int): Mine = Mine(Position(row, col))
    }
}

class PushableButton(
    position: Position
) : Button(position) {

    companion object {
        fun of(row: Int, col: Int): PushableButton = PushableButton(Position(row, col))
    }
}
