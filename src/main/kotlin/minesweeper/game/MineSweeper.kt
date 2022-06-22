package minesweeper.game

import minesweeper.domain.board.strategy.DefaultRandomMineStrategy
import minesweeper.view.ViewResolver

fun main() {
    val mineSweeperController = MineSweeperController(
        viewResolver = ViewResolver(),
        mineStrategy = DefaultRandomMineStrategy()
    )
    mineSweeperController.start()
}
