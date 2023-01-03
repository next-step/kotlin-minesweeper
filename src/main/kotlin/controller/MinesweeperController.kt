package controller

import domain.Board
import domain.BoardInfo
import domain.Game
import domain.GameStatus
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

        ResultView.printStart()
        play(board, game)
        if (game.status == GameStatus.WIN) return ResultView.printWin()
        return ResultView.printLose()
    }

    private fun play(board: Board, game: Game) {
        while (game.isProceeding) {
            val coordinate = InputView.inputCellToOpen()
            game.openCell(board, coordinate)
            ResultView.printBoard(BoardDto.from(board, game.boardInfo.column))
        }
    }
}

fun main() {
    MinesweeperController().run()
}
