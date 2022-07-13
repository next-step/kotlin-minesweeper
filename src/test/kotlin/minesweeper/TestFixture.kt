package minesweeper

import minesweeper.domain.Coordinate
import minesweeper.domain.CoordinateValue

fun Coordinate(
    x: Int,
    y: Int,
): Coordinate = Coordinate(
    x = CoordinateValue(value = x),
    y = CoordinateValue(value = y)
)
