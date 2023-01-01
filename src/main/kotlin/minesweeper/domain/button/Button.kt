package minesweeper.domain.button

import minesweeper.domain.position.Position

sealed class Button(
    val position: Position
) {
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
) : Button(position)

class PushableButton(
    position: Position
) : Button(position) {
    var aroundMineCount: Int? = null
        set(value) {
            require(field == null) {
                "AroundMineCount is already set"
            }

            field = value
        }
}
