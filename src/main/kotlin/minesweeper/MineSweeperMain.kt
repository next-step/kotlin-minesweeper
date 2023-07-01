package minesweeper

import minesweeper.domain.board.BoardRange
import minesweeper.domain.board.MineSweeperBoardGenerator
import minesweeper.domain.mine.MinePositionGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.readHeight()
    val width = inputView.readWidth()

    val boardRange = BoardRange(height = height, width = width)

    val mineQuantity = inputView.readMineQuantity()

    val minePositions =
        MinePositionGenerator.createMinePositions(mineQuantity = mineQuantity, boardRange = boardRange)
    val board = MineSweeperBoardGenerator.create(boardRange = boardRange, minePositions = minePositions)

    resultView.printBoard(board = board)
}
