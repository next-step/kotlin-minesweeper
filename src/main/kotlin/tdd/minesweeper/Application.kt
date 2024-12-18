package tdd.minesweeper

import tdd.minesweeper.domain.GameBoard
import tdd.minesweeper.view.InputView
import tdd.minesweeper.view.ResultView

class Application {
    fun run() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()

        ResultView.printGameStart()
        val game = GameBoard(height, width, mineCount)

        ResultView.printGrid(game.getGrid())
        while (true) {
            val (row, col) = InputView.getCoordinate()
            val result = game.openCell(row, col)

            ResultView.printGrid(game.getGrid())

            if (!result) {
                println("지뢰를 밟았습니다.")
                break
            }

            if (game.isClear()) {
                println("지뢰를 모두 찾았습니다.")
                break
            }
        }
    }
}
