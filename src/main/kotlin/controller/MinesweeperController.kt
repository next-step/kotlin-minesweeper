package controller

import domain.Blank
import domain.Board
import domain.BoardInfo
import domain.Game
import domain.GameStatus
import domain.Mine
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
        while (game.isProceeding) {
            play(board, game)
        }
        return if (game.status == GameStatus.WIN) ResultView.printWin() else ResultView.printLose()
    }

    private fun play(board: Board, game: Game) {
        val coordinate = InputView.inputCellToOpen()

        val cell = board.findOrNull(coordinate)
            ?: throw IllegalArgumentException(ERROR_MESSAGE_NOT_EXIST_COORDINATE)
        when (cell) {
            is Mine -> mineProcedure(board, game)
            is Blank -> blankProcedure(board, game, cell)
        }

        ResultView.printBoard(BoardDto.from(board, game.boardInfo.column))
    }

    private fun mineProcedure(board: Board, game: Game) {
        board.openAllMineCells()
        game.changeStatus(GameStatus.LOSE)
    }

    private fun blankProcedure(board: Board, game: Game, blank: Blank) {
        game.openBlankCell(board, blank)
        if (board.isOpenAllBlank()) {
            game.changeStatus(GameStatus.WIN)
            board.openRemainCells()
        }
    }

    companion object {
        private const val ERROR_MESSAGE_NOT_EXIST_COORDINATE = "존재하지 않는 좌표입니다."
    }
}

fun main() {
    MinesweeperController().run()
}
