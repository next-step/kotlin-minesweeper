package minesweeper

import minesweeper.controller.MineSweeper
import minesweeper.view.input.ConsoleBoardGenerator
import minesweeper.view.input.ConsoleInputView
import minesweeper.view.output.ConsoleOutputView

fun main() {
    MineSweeper(
        boardGenerator = ConsoleBoardGenerator,
        inputView = ConsoleInputView,
        outputView = ConsoleOutputView
    ).run()
}
