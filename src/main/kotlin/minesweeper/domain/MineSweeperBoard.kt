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
        buildSafeBlock()
    }

    private fun buildBoard(maxXAxis: Int, maxYAxis: Int): Map<Point, Block> {
        return (0 until maxXAxis).flatMap { currentXAixs ->
            buildLine(currentXAixs, maxYAxis)
        }.toMap()
    }

    private fun buildLine(currentXAxis: Int, maxYAxis: Int): List<Pair<Point, Block>> {
        return (0 until maxYAxis).map { y ->
            buildEmptyBlock(currentXAxis, y)
        }
    }

    private fun buildEmptyBlock(currentXAxis: Int, currentYAxis: Int): Pair<Point, Block> {
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

    private fun buildSafeBlock() {
        val emptyBlockPoints = findEmptyBlockPoint()
        emptyBlockPoints.forEach { currentPoint: Point ->
            _state[currentPoint] = SafeBlock(countNearMine(currentPoint.getNearPoints()))
        }
    }

    private fun findEmptyBlockPoint(): Set<Point> {
        return _state.filterValues { block: Block -> block is EmptyBlock }.keys
    }

    private fun countNearMine(points: List<Point>): Int {
        return points.filter { _state[it] is MineBlock }.size
    }

    private fun countMine(): Int {
        return _state.values.count { block: Block -> block is MineBlock }
    }
}
