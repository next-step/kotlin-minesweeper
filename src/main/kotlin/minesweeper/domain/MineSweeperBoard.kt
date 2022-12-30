package minesweeper.domain

import java.util.Random

class MineSweeperBoard(private val width: Int, private val height: Int, mineCount: Int = 0) {

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
        _mineCount = countMine()
    }

    private fun buildBoard(maxXAxis: Int, maxYAxis: Int): Map<Point, Block> {
        return (0 until maxXAxis).flatMap { currentXAixs ->
            buildLine(currentXAixs, maxYAxis)
        }.toMap()
    }

    private fun buildLine(currentXAxis: Int, maxYAxis: Int): List<Pair<Point, Block>> {
        return (0 until maxYAxis).map { y ->
            buildBlock(currentXAxis, y)
        }
    }

    private fun buildBlock(currentXAxis: Int, currentYAxis: Int): Pair<Point, Block> {
        return Point(currentXAxis, currentYAxis) to EmptyBlock()
    }

    private fun plantMines(plantingMineCount: Int) {
        while (countMine() < plantingMineCount) {
            val width = Random().nextInt(width)
            val height = Random().nextInt(height)
            validatePoint(Point(width, height))
            _state[Point(width, height)] = MineBlock()
        }
    }

    private fun validatePoint(point: Point) {
        if (!_state.containsKey(point)) {
            throw MineSweeperException(ExceptionReason.ILLEGAL_POINT)
        }
    }

    private fun countMine(): Int {
        return _state.values.count { block: Block -> block is MineBlock }
    }
}
