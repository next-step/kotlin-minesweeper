package minesweeper

import minesweeper.domain.cell.Coordinate
import minesweeper.domain.cell.CoordinateValue

fun Coordinate(
    x: Int,
    y: Int,
): Coordinate = Coordinate(
    x = CoordinateValue(value = x),
    y = CoordinateValue(value = y)
)
