package minsweeper.view

import minsweeper.domain.Coordinate

object CoordinateParser {

    fun parse(input: String): Coordinate {
        val (row, column) = input.split(",")
            .map {
                it.trim()
                    .toInt() - 1
            }
        return Coordinate(row, column)
    }

}