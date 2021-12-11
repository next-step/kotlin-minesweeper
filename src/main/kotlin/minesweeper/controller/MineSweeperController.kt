package minesweeper.controller

import minesweeper.domain.DefaultRandomGenerator
import minesweeper.domain.board.BoardFactory
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineCount
import minesweeper.domain.board.Width
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
            val (row, column) = InputView.getPosition()
            board = board.open(Position.from(row = row, column = column))
            OutputView.printBoard(BoardDto(board))
        }

        OutputView.printResult(board.getResult())
    }
}
