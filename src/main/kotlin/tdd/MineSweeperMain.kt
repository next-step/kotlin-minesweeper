package tdd

import tdd.domain.Board
import tdd.domain.Coordinate
import tdd.domain.randomCoordinates
import tdd.view.InputView
import tdd.view.ResultView
import tdd.vo.BoardVO

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val board = Board.create(height, width, mineCount, randomCoordinates(height, width))

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
    ResultView.printBoard(BoardVO(board))
}
