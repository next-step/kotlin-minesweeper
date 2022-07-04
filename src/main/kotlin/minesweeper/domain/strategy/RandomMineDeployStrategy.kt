package minesweeper.domain.strategy

import minesweeper.domain.cell.Coordinate

object RandomMineDeployStrategy : MineDeployStrategy {
    override fun execute(coordinates: List<Coordinate>, mineCount: Int): List<Coordinate> =
        coordinates.shuffled().take(mineCount)
}
