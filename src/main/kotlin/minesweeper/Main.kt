package minesweeper

import minesweeper.domain.MineSweeperMap
import minesweeper.ui.MineSweeperGameConfigurer
import minesweeper.ui.MineSweeperMapPrinter

fun main() {

    val height = MineSweeperGameConfigurer.height()
    println()

    val width = MineSweeperGameConfigurer.width()
    println()

    val mine = MineSweeperGameConfigurer.mine()
    println()

    val mineSweeperMap = MineSweeperMap.of(height, width, mine)

    MineSweeperMapPrinter.print(mineSweeperMap)
}
