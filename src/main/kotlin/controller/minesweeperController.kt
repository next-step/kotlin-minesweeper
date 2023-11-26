package controller

import domain.GameBoard
import domain.vo.BoardSettings
import view.InputView
import view.OutputView

fun main() {
    val gameBoard = gameSetUp()
    gameStart(gameBoard)
}

private fun gameSetUp(): GameBoard {
    OutputView.printEnterHeight()
    val height = InputView.inputNumber()
    OutputView.printEnterWidth()
    val width = InputView.inputNumber()
    OutputView.printEnterMineCount()
    val mineCount = InputView.inputNumber()
    val boardSettings = BoardSettings(height, width, mineCount)

    return GameBoard.createGameBoard(boardSettings)
}

private fun gameStart(gameBoard: GameBoard) {
    OutputView.printMineGameStart()
    OutputView.printGameBoard(gameBoard)
}
