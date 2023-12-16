package minesweeper.view

import minesweeper.model.point.Coordinate
import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

object CoordinateParser {
    fun parse(input: String): Coordinate {
        val (left, right) = input
            .split(",")
            .map { it.toInt() }
        return Coordinate(Vertical(left), Horizontal(right))
    }
}
