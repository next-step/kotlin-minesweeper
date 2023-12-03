package minesweeper.domain

import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeper(
    private val board: MineSweeperBoardSize,
) {
    fun createBoard(mineSweeperList: List<String>): MineSweeperBoard {
        return MineSweeperBoard.newInstance(board = board, mineSweeperList = mineSweeperList)
    }
}
