package minesweeper

import minesweeper.controller.MineSweeper
import minesweeper.view.input.ConsoleBoardBuilder
import minesweeper.view.input.ConsoleInputView
import minesweeper.view.output.ConsoleOutputView

fun main() {

    MineSweeper(
        boardBuilder = ConsoleBoardBuilder,
        inputView = ConsoleInputView(),
        outputView = ConsoleOutputView
    ).run()
}
