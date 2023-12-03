package minesweeper.model.board.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.board.MineDeployStrategy
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Points
import minesweeper.model.point.TileType
import minesweeper.model.vison.impl.VisionVeiledStrategy

class EvenlyStrategy(
    private val countOfMines: Int,
) : MineDeployStrategy {

    override fun deployPoints(boardLimit: BoardLimit): Points {
        requireMineCountLimit(boardLimit.area(), countOfMines)
        val coordinateTileTypeMap = (0 until boardLimit.area())
            .asSequence()
            .shuffled()
            .take(countOfMines)
            .map { coordinateOrderOf(it, boardLimit) to TileType.MINE }
            .toMap()
        requireMineCountDeployed(coordinateTileTypeMap.keys.size, countOfMines)
        return Points(coordinateTileTypeMap, VisionVeiledStrategy(boardLimit))
    }

    private fun requireMineCountDeployed(countOfMinesActual: Int, countOfMinesExpect: Int) {
        require(countOfMinesActual == countOfMinesExpect) { "실제 생성된 지뢰의 수 [$countOfMinesActual] != 생성 요청한 지뢰의 수 [$countOfMinesExpect]" }
    }

    private fun requireMineCountLimit(limitMineCounts: Int, countOfMines: Int) {
        require(limitMineCounts >= countOfMines) { "요청된 $countOfMines 개의 지뢰는 생성할 수 없습니다. 지뢰의 최대 생성 가능 수는 $limitMineCounts 개 입니다. " }
    }

    private fun coordinateOrderOf(order: Int, boardLimit: BoardLimit): Coordinate {
        return Coordinate.of(
            vertical = order / boardLimit.verticalLimit.value,
            horizontal = order % boardLimit.horizontalLimit.value
        )
    }
}
