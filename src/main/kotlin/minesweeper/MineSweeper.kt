package minesweeper

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineSweeperWidth
import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeper(
    private val board: MineSweeperBoardSize,
    private val mineCount: Int
) {
    init {
        require(mineCount > 0) { INVALID_VALUE }
    }

    fun createBoard(): List<MineSweeperWidth> {
        val notContainedMineCount = board.getBoardFullSize() - mineCount
        val notContainedMineList = MineSweeperWidth.newInstance(widthSize = notContainedMineCount, value = NOT_MINE)
        val mineList = MineSweeperWidth.newInstance(widthSize = mineCount, value = MINE)

        val mineSweeperList = (notContainedMineList + mineList).shuffled()

        return MineSweeperBoard.newInstance(board = board, mineSweeperList = mineSweeperList)
    }

    companion object {
        private const val INVALID_VALUE = "잘못된 값을 입력하셨습니다."
        private const val NOT_MINE = "C"
        private const val MINE = "*"
    }
}
