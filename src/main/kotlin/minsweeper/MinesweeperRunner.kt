package minsweeper

import minsweeper.domain.Board
import minsweeper.domain.BoardSize
import minsweeper.domain.Coordinate
import minsweeper.domain.OpenResult
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
        return Board(generator.generate(boardSize, mineAmount))
    }

    private fun play(board: Board) {
        ResultView.printGameStart()
        while (true) {
            val coordinate = Coordinate.of(InputView.showAndGetOpenCoordinate())
            when (board.open(coordinate)) {
                OpenResult.SUCCESS -> ResultView.printCells(board.coordinatedCells)
                OpenResult.MINE_FOUND -> {
                    ResultView.printLoseGame()
                    return
                }

                OpenResult.INVALID_COORDINATE -> ResultView.printEnterRightCoordinate(coordinate)
            }
        }
    }

}


fun main() {
    MinesweeperRunner().run()
}