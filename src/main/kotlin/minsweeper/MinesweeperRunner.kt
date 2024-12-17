package minsweeper

import minsweeper.domain.*
import minsweeper.view.InputView
import minsweeper.view.ResultView

class MinesweeperRunner {

    fun run() {
        val board = initializeBoard()
        ResultView.printStartGame()
        while (true) {
            when (board.open(InputView.showAndGetOpenCoordinate())) {
                is Cell.Island -> ResultView.printBoard(board.boardLines)
                Cell.Mine -> {
                    ResultView.printLoseGame()
                    return
                }
            }
        }
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

}

fun main() {
    MinesweeperRunner().run()
}
