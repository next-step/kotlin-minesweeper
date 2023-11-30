package minesweeper

import minesweeper.view.InputView
import minesweeper.view.MineSweeperController
import minesweeper.view.ResultView

fun main() {
    MineSweeperController(
        InputView(),
        ResultView(),
    )
}
