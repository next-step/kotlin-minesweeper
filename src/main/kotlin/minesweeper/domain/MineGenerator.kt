package minesweeper.domain

import minesweeper.domain.Height.Companion.MINIMUM_HEIGHT
import minesweeper.domain.Width.Companion.MINIMUM_WIDTH

object MineGenerator {

    // 높이와 너비로 주어진 지뢰만큼 랜덤으로 지뢰를 생성한다.
    fun generate(mineMap: MineMap, mineCount: Int): Mines {
        val mineY = List(mineMap.height()) { it + MINIMUM_HEIGHT }
        val mineX = List(mineMap.height()) { it + MINIMUM_WIDTH }
        val mines = mineY.flatMap { y ->
            createMines(mineX, y)
        }.shuffled().take(mineCount)
        return Mines(mines)
    }

    private fun createMines(mineX: List<Int>, y: Int): List<Mine> {
        return mineX.map { x ->
            Mine(x, y)
        }
    }
}
