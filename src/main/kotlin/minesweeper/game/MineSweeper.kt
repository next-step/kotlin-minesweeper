package minesweeper.game

import minesweeper.domain.board.random.DefaultRandomMineStrategy
import minesweeper.view.ViewResolver

fun main() {
    val mineSweeperController = MineSweeperController(
        viewResolver = ViewResolver(),
        randomMineStrategy = DefaultRandomMineStrategy()
    )
    mineSweeperController.start()
}
