package minesweeper.model.board.impl

import minesweeper.model.board.MineDeployStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Points

class EvenlyStrategy(
    private val countOfMines: Int,
) : MineDeployStrategy {

    override fun deployPoints(verticalLimit: Int, horizontalLimit: Int): Points {
        requireMineCountLimit(verticalLimit * horizontalLimit, countOfMines)
        val map = (0 until (verticalLimit * horizontalLimit))
            .asSequence()
            .shuffled()
            .take(countOfMines)
            .map { coordinateOrderOf(it, verticalLimit, horizontalLimit) to Attribute.MINE }
            .toMap()
        return Points(map)
    }

    private fun requireMineCountLimit(limitMineCounts: Int, countOfMines: Int) {
        require(limitMineCounts >= countOfMines) { "요청된 $countOfMines 개의 지뢰는 생성할 수 없습니다. 지뢰의 최대 생성 가능 수는 $limitMineCounts 개 입니다. " }
    }

    private fun coordinateOrderOf(order: Int, verticalLimit: Int, horizontalLimit: Int): Coordinate {
        return Coordinate.of(order / verticalLimit, order % horizontalLimit)
    }
}
