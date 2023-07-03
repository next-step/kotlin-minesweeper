package minesweeper

import minesweeper.controller.MineSweeperController
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val controller = MineSweeperController(
        inputView = inputView,
        resultView = resultView,
    )

    controller.playGame()
}
