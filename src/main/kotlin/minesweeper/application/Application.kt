package minesweeper.application

import minesweeper.domain.MineSweeperGame
import minesweeper.domain.RandomCoordinateGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

class Application {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val randomCoordinateGenerator = RandomCoordinateGenerator()

    fun run() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val minesCount = inputView.inputMinesCount()

        val mineSweeperGame = MineSweeperGame(height = height, width = width, mineCount = minesCount, randomCoordinateGenerator)
    }
}
