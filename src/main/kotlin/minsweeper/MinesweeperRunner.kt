package minsweeper

import minsweeper.domain.Board
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        val board = Board(
            InputView.showAndGetHeight(),
            InputView.showAndGetWidth(),
            InputView.showAndGetMineCount(),
        )

        ResultView.printBoard(board.boardLines)
    }

}

fun main() {
    MinesweeperRunner().run()
}
