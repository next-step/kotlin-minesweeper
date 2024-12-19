package minsweeper

import minsweeper.domain.*
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        startGame(initializeBoard())
    }

    private fun initializeBoard(): Board {
        val boardSize = BoardSize(
            InputView.showAndGetHeight(),
            InputView.showAndGetWidth(),
        )
        val mineCount = InputView.showAndGetMineCount()

        return Board.of(
            boardSize,
            mineCount,
            BoardLinesGenerator(aroundMineCountJudge = AroundMineCountJudge()),
        )
    }

    private fun startGame(board: Board) {
        ResultView.printStartGame()
        while (true) {
            val coordinate = Coordinate.of(InputView.showAndGetOpenCoordinate())
            val cell = board.open(coordinate)
            if (processCellAndCheckGameOver(cell, board)) return
        }
    }

    private fun processCellAndCheckGameOver(cell: Cell, board: Board): Boolean {
        return when (cell) {
            is Cell.Island -> {
                ResultView.printBoard(board.boardLines)
                false
            }

            Cell.Mine -> {
                ResultView.printLoseGame()
                true
            }
        }
    }

}

fun main() {
    MinesweeperRunner().run()
}
