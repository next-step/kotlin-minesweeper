package controller

import domain.Board
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

        ResultView.printStart()
        play(board, game)
    }

    private fun play(board: Board, game: Game) {
        while (!board.isOpenAllBlank) {
            val coordinate = InputView.inputCellToOpen()
            if (board.isMineCell(coordinate)) return ResultView.printLose()
            val blank = game.getBlankCell(coordinate, board)
            board.openAdjacentBlanksBy(blank)
            ResultView.printBoard(BoardDto.from(board, game.boardInfo.column))
        }
        board.openAllCells()
        ResultView.printWin(BoardDto.from(board, game.boardInfo.column))
    }
}

fun main() {
    MinesweeperController().run()
}
