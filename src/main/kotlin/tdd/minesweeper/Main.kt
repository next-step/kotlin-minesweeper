package tdd.minesweeper

import tdd.minesweeper.ui.ConsoleInputView
import tdd.minesweeper.ui.ConsoleOutputView
import tdd.minesweeper.ui.MineSweeperApp
import tdd.minesweeper.ui.RetryingInputView

fun main() {
    val app =
        MineSweeperApp(
            inputView = RetryingInputView(ConsoleInputView),
            outputView = ConsoleOutputView,
        )

    app.play()
}
