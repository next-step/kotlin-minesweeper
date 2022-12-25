package controller

import domain.BoardInfo
import domain.Game
import dto.BoardDto
import view.InputView
import view.ResultView

class MinesweeperController {
    fun run() {
        val row = InputView.inputHeight()
        val column = InputView.inputWidth()
        val mineCount = InputView.inputMineCount(row, column)
        val boardInfo = BoardInfo(row, column, mineCount)
        val game = Game(boardInfo)

        val board = game.createBoard()
        ResultView.printBoard(boardInfo, BoardDto.from(board))
    }
}

fun main() {
    MinesweeperController().run()
}
