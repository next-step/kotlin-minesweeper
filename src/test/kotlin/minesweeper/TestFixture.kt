package minesweeper

import minesweeper.domain.Coordinate
import minesweeper.domain.CoordinateValue
import minesweeper.dto.MineBoardLength

fun Coordinate(
    x: Int,
    y: Int,
): Coordinate = Coordinate(
    x = CoordinateValue(value = x),
    y = CoordinateValue(value = y)
)

object Coordinates {
    fun from(
        height: Int,
        width: Int,
    ): minesweeper.domain.Coordinates = minesweeper.domain.Coordinates.from(
        height = MineBoardLength(height),
        width = MineBoardLength(width)
    )
}
