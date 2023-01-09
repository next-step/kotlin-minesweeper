package minesweeper.domain

import minesweeper.domain.block.Block
import minesweeper.domain.block.MineBlock
import minesweeper.domain.block.SafeBlock
import minesweeper.domain.exception.ExceptionReason
import minesweeper.domain.exception.MineSweeperException
import minesweeper.domain.plant_strategy.PlantStrategy
import minesweeper.domain.plant_strategy.RemainderPlantStrategy

class MineSweeperBoard(
    private val width: Int,
    private val height: Int,
    mineCount: Int = 0,
    private val strategy: PlantStrategy = RemainderPlantStrategy()
) {

    private val _state: MutableMap<Point, Block>

    val state: Map<Point, Block>
        get() = _state.toMap()

    private var _mineCount: Int = 0

    val mineCount
        get() = _mineCount

    init {
        if (width * height < mineCount) {
            throw MineSweeperException(ExceptionReason.MINE_COUNT_OVER_BLOCKS)
        }
        _state = buildBoard(width, height).toMutableMap()
        plantMines(mineCount)
    }

    private fun buildBoard(maxXAxis: Int, maxYAxis: Int): Map<Point, Block> {
        return (0 until maxXAxis).flatMap { currentXAxis ->
            buildLine(currentXAxis, maxYAxis)
        }.toMap()
    }

    private fun buildLine(currentXAxis: Int, maxYAxis: Int): List<Pair<Point, Block>> {
        return (0 until maxYAxis).map { y ->
            buildBlock(currentXAxis, y)
        }
    }

    private fun buildBlock(currentXAxis: Int, currentYAxis: Int): Pair<Point, Block> {
        return Point(currentXAxis, currentYAxis) to SafeBlock()
    }

    private fun plantMines(plantingMineCount: Int) {
        val minePoints = strategy.createMines(width, height, plantingMineCount)
        minePoints.forEach {
            _state[it] = MineBlock()
        }
        _mineCount = countMine()
        updateSafeBlock()
    }

    private fun updateSafeBlock() {
        val safeBlockPoints = _state.filterValues { block: Block -> block is SafeBlock }.keys
        safeBlockPoints.forEach { currentPoint: Point ->
            _state[currentPoint] = SafeBlock(sumNearPointMines(currentPoint.getNearPoints(width, height)))
        }
    }

    private fun sumNearPointMines(points: List<Point>): Int {
        return points.count { _state[it] is MineBlock }
    }

    private fun countMine(): Int {
        return _state.values.count { block: Block -> block is MineBlock }
    }
}
