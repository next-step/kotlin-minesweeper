package minsweeper

import minsweeper.domain.board.Board
import minsweeper.domain.board.BoardSize
import minsweeper.domain.Coordinate
import minsweeper.domain.result.OpenResult
import minsweeper.domain.generate.CoordinatedCellsGenerator
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        val board = initialize()
        play(board)
    }

    private fun initialize(): Board {
        val boardSize = BoardSize(
            InputView.showAndGetHeight(),
            InputView.showAndGetWidth(),
        )
        val mineAmount = InputView.showAndGetMineAmount()

        val generator = CoordinatedCellsGenerator()
        return Board(boardSize, generator.generate(boardSize, mineAmount))
    }

    private fun play(board: Board) {
        ResultView.printGameStart()
        while (true) {
            val coordinate = Coordinate.of(InputView.showAndGetOpenCoordinate())
            val result = board.open(coordinate)
                .also { result -> result.print(board, coordinate) }

            if (result == OpenResult.MINE_FOUND) {
                return
            }
        }
    }

    private fun OpenResult.print(board: Board, enteredCoordinate: Coordinate): Unit = when (this) {
        OpenResult.SUCCESS -> ResultView.printCells(board.coordinatedCells)
        OpenResult.MINE_FOUND -> ResultView.printLoseGame()
        OpenResult.INVALID_COORDINATE -> ResultView.printEnterRightCoordinate(enteredCoordinate)
    }

}


fun main() {
    MinesweeperRunner().run()
}