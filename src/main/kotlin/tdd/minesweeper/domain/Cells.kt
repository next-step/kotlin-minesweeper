package tdd.minesweeper.domain

import tdd.minesweeper.domain.strategy.MinePlaceStrategy
import tdd.minesweeper.domain.strategy.RandomMinePlaceStrategy

class Cells(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
    private val minePlaceStrategy: MinePlaceStrategy = RandomMinePlaceStrategy(),
) {
    private val grid: MutableList<MutableList<Cell>> =
        MutableList(height) { MutableList(width) { Land() } }

    init {
        val minePlaces = minePlaceStrategy.calcMinePlace(height, width, mineCount)
        minePlaces.forEach { (row, col) -> plantMine(row, col) }
    }

    private fun plantMine(row: Int, col: Int) {
        grid[row][col] = Mine()
    }

    fun getActiveMineCount(): Int {
        return grid.flatten()
            .filter { !it.isOpened() }
            .count { it is Mine }
    }
}
