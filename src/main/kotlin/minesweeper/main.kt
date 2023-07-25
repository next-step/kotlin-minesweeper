package minesweeper

import minesweeper.domain.Minesweeper
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    val input = InputView()
    val output = OutputView()

    val minesweeper = Minesweeper(input, output)
    minesweeper.start()
}
