package minsweeper

import minsweeper.domain.Board
import minsweeper.domain.BoardLinesGenerator
import minsweeper.domain.BoardSize
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        val boardSize = BoardSize(
            InputView.showAndGetHeight(),
            InputView.showAndGetWidth(),
        )
        val mineCount = InputView.showAndGetMineCount()

        val board = Board.of(
            boardSize,
            mineCount,
            BoardLinesGenerator(),
        )

        ResultView.printBoard(board.boardLines)
    }

}

fun main() {
    MinesweeperRunner().run()
}
