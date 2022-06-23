package minesweeper.game

import minesweeper.domain.board.RandomMineMaker
import minesweeper.view.ViewResolver

fun main() {
    val mineSweeperController = MineSweeperController(
        viewResolver = ViewResolver(),
        mineMaker = RandomMineMaker()
    )
    mineSweeperController.start()
}
