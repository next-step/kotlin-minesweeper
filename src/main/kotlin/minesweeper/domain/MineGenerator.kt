package minesweeper.domain

import minesweeper.domain.BlockMap.Companion.MIN_MINE_COUNT
import minesweeper.model.Point

interface MineGenerator {
    fun generate(mineCount: Int, height: Int, width: Int): List<Point>
}

class RandomMineGenerator() : MineGenerator {

    override fun generate(mineCount: Int, height: Int, width: Int): List<Point> {
        require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 ${MIN_MINE_COUNT}개 이상이어야 합니다." }
        require(mineCount <= maxMineCount(width, height)) { "지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다." }

        return mutableListOf<Point>().apply {
            do {
                val mine = (0 until width * height).random()
                val minePoint = Point(mine / width, mine % width)
                if (!contains(minePoint)) add(minePoint)
            } while (size < mineCount)
        }
    }
}
