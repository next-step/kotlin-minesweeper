package controller

import domain.Game
import dto.BoardDto
import view.InputView
import view.ResultView

class MinesweeperController {
    fun run() {
        val row = InputView.inputHeight()
        val column = InputView.inputWidth()
        val mineCount = InputView.inputMineCount(row, column)

        val game = Game(row, column, mineCount)
        val board = game.createBoard()
        ResultView.printBoard(game, BoardDto.from(board))
    }
}

fun main() {
    MinesweeperController().run()
}
