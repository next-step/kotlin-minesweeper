package minesweeper.domain.strategy

import minesweeper.domain.cell.Coordinate

fun interface MineDeployStrategy {
    fun execute(coordinates: List<Coordinate>, mineCount: Int): List<Coordinate>
}
