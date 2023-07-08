package minesweeper.domain

import minesweeper.domain.MineBoardStatus.IN_PROGRESS
import minesweeper.domain.MineBoardStatus.LOSE
import minesweeper.domain.MineBoardStatus.WIN

class MineBoardInfo(
    val height: Int,
    val width: Int,
    var toFindCellCount: Int = height * width,
    var mineBoardStatus: MineBoardStatus = IN_PROGRESS,
) {
    init {
        require(height >= MINIMUM_HEIGHT) { "지뢰찾기맵 높이는 ${MINIMUM_HEIGHT}이상이어야 합니다." }
        require(width >= MINIMUM_WIDTH) { "지뢰찾기맵 너비는 ${MINIMUM_WIDTH}이상이어야 합니다." }
    }

    fun isEnd(): Boolean = mineBoardStatus.isEnd

    fun toLose() {
        mineBoardStatus = LOSE
    }

    fun toWin() {
        mineBoardStatus = WIN
    }

    companion object {
        private const val MINIMUM_HEIGHT = 1
        private const val MINIMUM_WIDTH = 1
    }
}
