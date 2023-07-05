package minesweeper.domain

import minesweeper.error.MineSweeperErrorMessage.NUMBER_OF_MINES_MUST_BE_LESS_THAN_CELLS
import kotlin.random.Random

class RandomCoordinatesGenerator(
    private val maxHeight: Int,
    private val maxWidth: Int,
) : CoordinatesGenerator {

    fun create(numberOfMines: Int): Coordinates {
        require(numberOfMines < maxHeight * maxWidth) { NUMBER_OF_MINES_MUST_BE_LESS_THAN_CELLS }

        val coordinates = mutableSetOf<Coordinate>()
        val random = Random

        while (coordinates.size < numberOfMines) {
            val x = random.nextInt(maxWidth)
            val y = random.nextInt(maxHeight)
            val coordinate = Coordinate.of(x, y)
            coordinates.add(coordinate)
        }

        return Coordinates(coordinates)
    }
}
