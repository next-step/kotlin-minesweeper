package minesweeper.domain.strategy

import minesweeper.domain.cell.Coordinates

fun interface MineDeployStrategy {
    fun execute(coordinates: Coordinates, mineCount: Int): Coordinates
}
