package minesweeper

import minesweeper.view.InputView
import minesweeper.view.ResultView

/**
 * @author 이상준
 */
class MinesWeeperGame(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun start() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val minesWeeper = MinesWeeper(height, width, mineCount)
        val cells = minesWeeper.createCells()
        resultView.printBoard(cells)
    }
}

fun main() {
    MinesWeeperGame(InputView(), ResultView()).start()
}
