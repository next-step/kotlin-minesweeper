package minesweeper.domain

import minesweeper.error.MineSweeperErrorMessage.NUMBER_OF_MINES_MUST_BE_LESS_THAN_CELLS

class FixedCoordinatesGenerator(
    private val maxHeight: Int,
    private val maxWidth: Int,
) : CoordinatesGenerator {

    fun create(coordinates: List<Pair<Int, Int>>): Coordinates {
        val numberOfMines = coordinates.size
        require(numberOfMines < maxHeight * maxWidth) { NUMBER_OF_MINES_MUST_BE_LESS_THAN_CELLS }

        val coordinateSet = buildSet {
            coordinates.forEach {
                add(Coordinate.of(it.second, it.first))
            }
        }

        return Coordinates(coordinateSet)
    }
}
