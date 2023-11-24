package controller

import domain.GameBoard
import view.InputView
import view.OutputView

class MinesweeperGame(private val inputView: InputView, private val outputView: OutputView) {
    fun startGame() {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        val gameBoard = GameBoard(height, width)
        gameBoard.placeMines(mineCount)
        outputView.displayBoard(gameBoard)
    }
}
