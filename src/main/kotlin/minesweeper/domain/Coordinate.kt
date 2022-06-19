package minesweeper.domain

import minesweeper.domain.CoordinateIndex.Companion.START_INDEX

data class Coordinate(val x: CoordinateIndex, val y: CoordinateIndex) {

    companion object {
        fun listOf(height: Int, width: Int): List<Coordinate> {
            return (START_INDEX until height).flatMap { y ->
                coordinates(width, y)
            }
        }

        private fun coordinates(width: Int, y: Int): List<Coordinate> {
            return (START_INDEX until width).map { x ->
                Coordinate(CoordinateIndex(x), CoordinateIndex(y))
            }
        }
    }
}
