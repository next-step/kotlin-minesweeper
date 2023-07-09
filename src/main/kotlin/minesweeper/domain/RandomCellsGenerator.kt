package minesweeper.domain

import minesweeper.domain.vo.PositiveNumber

object RandomCellsGenerator {
    fun generate(totalCount: PositiveNumber, mineCount: PositiveNumber): List<Cell> {
        val mines = List(mineCount.value) { Cell.mine }
        val noMines = List(totalCount.value - mineCount.value) { Cell.nonMine }
        return (mines + noMines).shuffled()
    }
}
