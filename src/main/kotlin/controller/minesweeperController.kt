package controller

import domain.GameBoard
import domain.BoardSettings
import domain.Point
import domain.strategyImpl.RandomPointFactory
import dto.GameBoardDto
import view.InputView
import view.OutputView

fun main() {
    val gameBoard = gameSetUp()
    val gameBoardDto = GameBoardDto(gameBoard)
    gameStart(gameBoardDto)
}

private fun gameSetUp(): GameBoard {
    OutputView.printEnterHeight()
    val height = InputView.inputNumber()
    OutputView.printEnterWidth()
    val width = InputView.inputNumber()
    OutputView.printEnterMineCount()
    val mineCount = InputView.inputNumber()
    val boardSettings = BoardSettings(height, width, mineCount)

    return GameBoard.createGameBoard(boardSettings, RandomPointFactory())
}

private fun gameStart(gameBoard: GameBoardDto) {
    OutputView.printMineGameStart()
    OutputView.printOpen()
    val inputPoint = InputView.inputPoint()
    val point = Point.parsePoint(inputPoint)


    OutputView.printGameBoard(gameBoard)
}
