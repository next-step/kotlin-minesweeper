package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width

interface MineGenerator {
    fun generate(mineCount: MineCount, height: Height, width: Width): List<Point>
}

class RandomMineGenerator : MineGenerator {

    override fun generate(mineCount: MineCount, height: Height, width: Width): List<Point> {
        require(mineCount.value <= maxMineCount(width.value, height.value)) { "지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다." }

        return mutableListOf<Point>().apply {
            do {
                val mine = (0 until width.value * height.value).random()
                val minePoint = Point(mine / width.value, mine % width.value)
                if (!contains(minePoint)) add(minePoint)
            } while (size < mineCount.value)
        }
    }

    private fun maxMineCount(width: Int, height: Int): Int = width * height
}
