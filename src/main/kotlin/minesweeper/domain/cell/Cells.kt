package minesweeper.domain.cell

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    fun toPositions() = cells.map { it.position }

    fun isOpenedMine(): Boolean = cells.any { it.isOpenedMineCell() }

    fun isAllOpenedExcludeMine(): Boolean {
        return cells.count { !it.isHiddenCell() } == cells.count { it.isNotMineCell() }
    }

    fun inputMineCells(mineCells: Cells) = cells.map { it.updateCellStatus(mineCells) }

    fun open(position: Position) {
        require(cells.any { it.position == position }) { NOT_FOUND_CELL }
        cells.first { it.position == position }.apply {
            this.openCell()
            openAdjacentCells(this)
        }
    }

    private fun openAdjacentCells(cell: Cell) {
        if (cell.getCellAdjacentCount() == BLANK_COUNT) {
            toCells(cell).forEach {
                if (it.getCellAdjacentCount() != MINE_COUNT && it.isHiddenCell()) {
                    it.openCell()
                    openAdjacentCells(it)
                }
            }
        }
    }

    private fun toCells(cell: Cell) = cells.filter {
        cell.isContainsAdjacentPositions(it.position)
    }

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
        private const val NOT_FOUND_CELL = "해당 셀을 찾을 수 없습니다."
        private const val BLANK_COUNT = 0
        private const val MINE_COUNT = -1
    }
}
