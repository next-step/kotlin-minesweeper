package controller

import domain.GameBoard
import domain.MineManager
import view.InputView
import view.OutputView

class MinesweeperGame(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val mineManager: MineManager
) {
    fun startGame() {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        val gameBoard = GameBoard(mineManager)
        gameBoard.initializeBoard(height, width, mineCount)
        outputView.displayBoard(gameBoard)
    }
}
