package minesweeper.domain

class MineSweeper(
    private val mineSweeperBoard: MineSweeperBoard
) {
    fun doMineSweeper(): MineSweeperBoard {
        return mineSweeperBoard.resultBoard()
    }
}
