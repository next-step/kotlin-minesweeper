package minesweeper.domain.board

import minesweeper.domain.RandomGenerator
import minesweeper.domain.cell.CellFactory
import minesweeper.domain.position.Positions

class BoardFactory(private val randomGenerator: RandomGenerator) {

    fun createBy(height: Height, width: Width, mineCount: MineCount): Board {
        val mineIndexes = getRandomIndexes(height, width, mineCount)
        val positions = Positions.from(height, width)
        val cells = CellFactory.getCells(positions, mineIndexes)
        return BoardInGame(cells)
    }

    private fun getRandomIndexes(height: Height, width: Width, mineCount: MineCount): List<Int> {
        val cellCount = height.value * width.value
        return randomGenerator.generate(until = cellCount, count = mineCount.value)
    }
}
