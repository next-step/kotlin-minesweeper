package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.RandomMineCoordinateGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView
import minesweeper.vo.BoardVO

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val board = Board.create(height, width, mineCount, mineCoordinateGenerator)

    ResultView.printStartGame()
    playGame(board)
}

private fun playGame(board: Board) {
    while (board.isRunning()) {
        ResultView.printBoard(BoardVO(board))

        val (row, col) = InputView.readCoordinateToOpen()
        val coordinate = Coordinate(row, col)
        if (board.hasMine(coordinate)) {
            ResultView.printLoseGame()
            return
        }
        board.open(coordinate)
    }
    ResultView.printWinGame()
}
