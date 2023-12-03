package minesweeper

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineSweeperWidth
import minesweeper.domain.board.size.MineSweeperBoardSize
import minesweeper.domain.mine.Mine

class MineSweeper(
    private val board: MineSweeperBoardSize,
    private val mine: Mine,
) {

    fun createBoard(): MineSweeperBoard {
        val notContainedMineCount = board.getBoardFullSize() - mine.mineCount
        val notContainedMineList =
            MineSweeperWidth.newInstance(widthSize = notContainedMineCount)
        val mineList = MineSweeperWidth.newInstance(widthSize = mine.mineCount, mineSweeperShape = mine.mineShape)

        val mineSweeperList = (notContainedMineList + mineList).shuffled()

        return MineSweeperBoard.newInstance(board = board, mineSweeperList = mineSweeperList)
    }
}
