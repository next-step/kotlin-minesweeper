package minesweeper.domain

import minesweeper.domain.board.Mine
import minesweeper.domain.vo.PositiveNumber

object RandomMinesGenerator {
    fun generate(totalCount: PositiveNumber, mineCount: PositiveNumber): List<Mine> {
        val mines = List(mineCount.value) { Mine.ACTIVE }
        val noMines = List(totalCount.value - mineCount.value) { Mine.INACTIVE }
        return (mines + noMines).shuffled()
    }
}
