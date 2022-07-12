package minesweeper.domain

object MineSweeperResult {
    fun isWin(mineBoard: MineBoard): Boolean = mineBoard.nonExistOpenedMine()
}
