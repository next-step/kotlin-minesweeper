package controller

import domain.GameBoard
import domain.BoardSettings
import domain.Point
import domain.strategyImpl.RandomPointFactory
import dto.GameBoardDto
import dto.GameResultDto
import view.InputView
import view.OutputView

fun main() {
    val gameBoard = gameSetUp()
    val gameResult = gameStart(gameBoard)
    OutputView.printGameResult(gameResult)
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

private fun gameStart(gameBoard: GameBoard): GameResultDto {
    OutputView.printMineGameStart()
    while (gameBoard.isContinued()) {
        openCells(gameBoard)
        printGameBoard(gameBoard)
    }

    return GameResultDto(gameBoard)
}

private fun printGameBoard(gameBoard: GameBoard) {
    val gameBoardDto = GameBoardDto(gameBoard)
    OutputView.printGameBoard(gameBoardDto)
}

private fun openCells(gameBoard: GameBoard) {
    OutputView.printOpen()
    val inputPoint = InputView.inputPoint()
    val point = Point.parsePoint(inputPoint)
    gameBoard.openCells(point)
}
