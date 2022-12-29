package controller

import domain.BoardInfo
import domain.Game
import domain.strategy.RandomGenerateStrategy
import dto.BoardDto
import view.InputView
import view.ResultView

class MinesweeperController {
    fun run() {
        val row = InputView.inputHeight()
        val column = InputView.inputWidth()
        val mineCount = InputView.inputMineCount(row, column)
        val boardInfo = BoardInfo(row, column, mineCount)
        val game = Game(boardInfo, RandomGenerateStrategy())

        val board = game.createBoard()
        game.markMinesAroundCountInBoard(board)
        ResultView.printBoard(boardInfo, BoardDto.from(board))
    }
}

fun main() {
    MinesweeperController().run()
}
