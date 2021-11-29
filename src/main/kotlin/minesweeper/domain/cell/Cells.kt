package minesweeper.domain.cell

import minesweeper.domain.position.Positions

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    fun toPositions() = cells.map { it.position }

    fun change(mineCells: Cells) = cells.map { it.updateCellStatus(mineCells) }

    companion object {
        fun makeMineCells(cells: Cells, mineCount: Int): Cells {
            require(cells.size >= mineCount) { OVER_COUNT_MESSAGE }
            return Cells(cells.shuffled().take(mineCount))
        }

        fun of(positions: Positions): Cells =
            positions.map {
                Cell.of(it)
            }.run {
                Cells(this)
            }

        private const val OVER_COUNT_MESSAGE = "카운트 수가 전체 수보다 큽니다."
    }
}
