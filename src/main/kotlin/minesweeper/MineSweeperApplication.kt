package minesweeper

import minesweeper.controller.GameController
import minesweeper.view.ConsoleInput
import minesweeper.view.ConsoleOutput

fun main() {
    val consoleInput = ConsoleInput()
    val consoleOutput = ConsoleOutput()
    val gameController = GameController(consoleInput, consoleOutput)

    gameController.start()
}
