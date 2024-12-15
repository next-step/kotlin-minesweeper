package minesweeper

import minesweeper.application.MinesweeperService
import minesweeper.application.RandomBoardGenerator

object ApplicationContext {
    private fun boardGenerator() = RandomBoardGenerator()

    private fun minesweeperService() = MinesweeperService(boardGenerator())

    fun minesweeperController() = MinesweeperController(minesweeperService())
}

fun main() {
    ApplicationContext
        .minesweeperController()
        .start()
}
