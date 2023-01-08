package minesweeper.domain

import minesweeper.common.value.CoordinateValue

data class Position(
    val x: CoordinateValue,
    val y: CoordinateValue
) {
    companion object {
        fun of(x: Int, y: Int): Position = Position(
            x = CoordinateValue(x),
            y = CoordinateValue(y)
        )
    }
}
