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
        while (board.open(InputView.showAndGetOpenCoordinate()) is Cell.Island) {
            ResultView.printBoard(board.boardLines)
        }

        ResultView.printLoseGame()
    }

}

fun main() {
    MinesweeperRunner().run()
}
