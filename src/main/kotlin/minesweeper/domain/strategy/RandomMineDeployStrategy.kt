package minesweeper.domain.strategy

import minesweeper.domain.cell.Coordinates

object RandomMineDeployStrategy : MineDeployStrategy {
    override fun execute(coordinates: Coordinates, mineCount: Int): Coordinates =
        coordinates.shuffledAndTake(count = mineCount)
}
