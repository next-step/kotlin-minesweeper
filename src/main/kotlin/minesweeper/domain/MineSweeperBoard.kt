package minesweeper.domain

import java.util.Random

class MineSweeperBoard(private val width: Int, private val height: Int, mineCount: Int = 0) {

    private val _state: MutableMap<Point, Block>

    val state: Map<Point, Block>
        get() = _state.toMap()

    private var _mineCount: Int

    val mineCount
        get() = countMine()

    init {
        require(width * height >= mineCount) {
            "지뢰의 개수는 총 블록의 개수보다 많을 수 없습니다."
        }
        _mineCount = 0
        _state = buildBoard(width, height).toMutableMap()
        plantMines(mineCount)
    }

    private fun buildBoard(maxXAxis: Int, maxYAxis: Int): Map<Point, Block> {
        val pairs = mutableListOf<Pair<Point, Block>>()

        (0 until maxXAxis).forEach { currentXAixs ->
            pairs.addAll(buildLine(currentXAixs, maxYAxis))
        }

        return pairs.toMap()
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
        while (mineCount < plantingMineCount) {
            val width = Random().nextInt(width)
            val height = Random().nextInt(height)
            _state[Point(width, height)] = MineBlock()
        }
    }

    private fun countMine(): Int {
        return _state.values.count { block: Block -> block is MineBlock }
    }
}
