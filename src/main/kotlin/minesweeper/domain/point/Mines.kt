package minesweeper.domain.point

import minesweeper.domain.MineGenerator

class Mines(
    height: Int,
    width: Int,
    count: Int,
    mineGenerator: MineGenerator,
) {
    val placedMines: List<Mine> = mineGenerator.generate(height, width, count)

    init {
        require(count >= ZERO) { MINE_COUNT_MIN_EXCEPTION }
        require(count <= width * height) { MINE_COUNT_OVER_EXCEPTION }
    }

    companion object {
        private const val ZERO = 0
        private const val MINE_COUNT_MIN_EXCEPTION = "지뢰 개수는 음수가 될 수 없습니다."
        private const val MINE_COUNT_OVER_EXCEPTION = "지뢰 개수는 전체 좌표 수보다 클 수 없습니다."
    }
}
