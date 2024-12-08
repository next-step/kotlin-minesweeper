package minesweeper.domain.point

import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.MineGenerator
import minesweeper.domain.Width

class Mines(
    height: Height,
    width: Width,
    count: MineCount,
    mineGenerator: MineGenerator,
) {
    operator fun contains(mine: Mine): Boolean = placedMines.contains(mine)

    private val placedMines: List<Mine> = mineGenerator.generate(height, width, count)

    init {
        require(count <= width.value * height.value) { MINE_COUNT_OVER_EXCEPTION }
    }

    companion object {
        private const val MINE_COUNT_OVER_EXCEPTION = "지뢰 개수는 전체 좌표 수보다 클 수 없습니다."
    }
}
