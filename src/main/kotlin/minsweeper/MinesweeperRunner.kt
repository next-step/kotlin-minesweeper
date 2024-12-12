package minsweeper

import minsweeper.domain.Board
import minsweeper.domain.BoardParam
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        val boardParam = BoardParam(
            InputView.showAndGetHeight(),
            InputView.showAndGetWidth(),
            InputView.showAndGetMineCount(),
        )

        val board = Board(boardParam)

        ResultView.printBoard(board.boardLines)
    }

}

fun main() {
    MinesweeperRunner().run()
}
