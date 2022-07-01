package minesweeper.game

import minesweeper.domain.cell.RandomCellMaker
import minesweeper.view.ViewResolver

fun main() {
    val mineSweeperController = MineSweeperController(
        viewResolver = ViewResolver(),
        cellMaker = RandomCellMaker
    )
    mineSweeperController.start()
}
