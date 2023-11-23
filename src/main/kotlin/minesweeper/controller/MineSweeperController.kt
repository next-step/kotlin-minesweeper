package minesweeper.controller

import minesweeper.model.MineSweeperGame
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {
    fun start() {
        val game = MineSweeperGame(InputView.inputHeight(), InputView.inputWidth(), InputView.inputCountOfMine())
        OutputView.printMap(game.minefield)
    }
}

fun main() {
    MineSweeperController().start()
}
