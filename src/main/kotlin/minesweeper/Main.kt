package minesweeper

import minesweeper.controller.MineSweeper
import minesweeper.view.input.ConsoleBoardBuilder
import minesweeper.view.output.ConsoleOutputView

fun main() {

    MineSweeper(
        boardBuilder = ConsoleBoardBuilder,
        outputView = ConsoleOutputView
    ).run()
}
