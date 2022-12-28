package minesweeper.domain

import minesweeper.domain.BlockMap.Companion.MIN_MINE_COUNT
import minesweeper.model.Point

interface MineGenerator {
    fun generate(): List<Point>
}

class RandomMineGenerator(
    private val mineCount: Int,
    private val width: Int,
    private val height: Int,
) : MineGenerator {

    init {
        require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 ${MIN_MINE_COUNT}개 이상이어야 합니다." }
        require(mineCount <= maxMineCount(width, height)) { "지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다." }
    }

    override fun generate(): List<Point> =
        mutableListOf<Point>().apply {
            do {
                val mine = (0 until width * height).random()
                val minePoint = Point(mine / width, mine % width)
                if (!contains(minePoint)) add(minePoint)
            } while (size < mineCount)
        }
}
