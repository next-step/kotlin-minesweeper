package minesweeper

import minesweeper.ui.ConsoleInputView
import minesweeper.ui.ConsoleResultView

fun main() {
    val consoleInputView = ConsoleInputView()
    val consoleResultView = ConsoleResultView()

    val controller = Controller(consoleInputView, consoleResultView)
    controller.drawMineSweeper()
}
