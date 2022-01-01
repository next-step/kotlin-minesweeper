package minesweeper.controller

import minesweeper.domain.DefaultRandomGenerator
import minesweeper.domain.board.*
import minesweeper.domain.position.Position
import minesweeper.view.BoardDto
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperController {

    private val boardFactory = BoardFactory(DefaultRandomGenerator)

    fun start() {
        val height = Height(InputView.getHeight())
        val width = Width(InputView.getWidth())
        val mineCount = MineCount(InputView.getMineCount())

        var board = boardFactory.createBy(height, width, mineCount)

        OutputView.printStart(BoardDto(board))

        while (!board.isFinish) {
            val action = InputView.getAction()
            val (row, column) = InputView.getPosition()
            val position = Position.from(row = row, column = column)
            board = action(action, board, position)
            OutputView.printBoard(BoardDto(board))
        }

        OutputView.printResult(board.getResult())
    }

    private fun action(action: String, board: Board, position: Position): Board {
        return when(action) {
            "open" -> board.open(position)
            "flag" -> board.flag(position)
            else -> throw IllegalArgumentException("not valid action")
        }
    }
}
