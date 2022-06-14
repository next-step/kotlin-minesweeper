package minesweeper

import minesweeper.controller.MineSweeper
import minesweeper.view.input.ConsoleMineMapBuilder
import minesweeper.view.output.ConsoleOutputView

fun main() {

    MineSweeper(
        mineMapBuilder = ConsoleMineMapBuilder,
        outputView = ConsoleOutputView
    ).run()
}
