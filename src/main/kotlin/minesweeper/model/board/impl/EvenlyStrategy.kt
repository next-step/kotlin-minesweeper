package minesweeper.model.board.impl

import minesweeper.model.board.MineDeployStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Points

class EvenlyStrategy(
    private val countOfMines: Int,
) : MineDeployStrategy {

    override fun deployPoints(verticalLimit: Int, horizontalLimit: Int): Points {
        val map = (0 until (verticalLimit * horizontalLimit))
            .asSequence()
            .shuffled()
            .take(countOfMines)
            .map { coordinateOrderOf(it, verticalLimit, horizontalLimit) to Attribute.MINE }
            .toMap()
        return Points(map)
    }

    private fun coordinateOrderOf(order: Int, verticalLimit: Int, horizontalLimit: Int): Coordinate {
        return Coordinate.of(order / verticalLimit, order % horizontalLimit)
    }
}
