package minesweeper

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineSweeperWidth
import minesweeper.domain.board.size.MineSweeperBoardSize
import minesweeper.domain.mine.Mine
import minesweeper.domain.mine.MineShape

class MineSweeper(
    private val board: MineSweeperBoardSize,
    private val mine: Mine,
) {

    fun createBoard(): MineSweeperBoard {
        val notContainedMineCount = board.getBoardFullSize() - mine.mineCount
        val notContainedMineList =
            MineSweeperWidth.newInstance(widthSize = notContainedMineCount, mineShape = MineShape.SHAPE)
        val mineList = MineSweeperWidth.newInstance(widthSize = mine.mineCount, mineShape = MineShape.MINE)

        val mineSweeperList = (notContainedMineList + mineList).shuffled()

        return MineSweeperBoard.newInstance(board = board, mineSweeperList = mineSweeperList)
    }
}
