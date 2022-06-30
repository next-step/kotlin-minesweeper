package minesweeper.game

import minesweeper.view.ViewResolver

fun main() {
    val mineSweeperController = MineSweeperController(
        viewResolver = ViewResolver()
    )
    mineSweeperController.start()
}
