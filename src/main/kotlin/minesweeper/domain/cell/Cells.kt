package minesweeper.domain.cell

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    fun toPositions() = cells.map { it.position }

    fun open(position: Position): CellsState {
        require(cells.any { it.position == position }) { NOT_FOUND_CELL }
        cells.first { it.position == position }.apply {
            this.openCell()
            openAdjacentCells(this)
        }
        return checkCellsState()
    }

    private fun openAdjacentCells(cell: Cell) {
        if (cell.getCellAdjacentCount() == BLANK_COUNT) {
            toCells(cell).forEach {
                if (it.isNotMineCell() && it.isHiddenCell) {
                    it.openCell()
                    openAdjacentCells(it)
                }
            }
        }
    }

    private fun toCells(cell: Cell) = cells.filter {
        cell.isContainsAdjacentPositions(it.position)
    }

    private fun checkCellsState() = when {
        isOpenedMine() -> CellsState.BOMB
        isAllOpenedExcludeMine() -> CellsState.NOT_EXIST_MINE
        else -> CellsState.EXIST_MINE
    }

    private fun isOpenedMine(): Boolean = cells.any { it.isOpenedMineCell() }

    private fun isAllOpenedExcludeMine(): Boolean {
        return cells.count { !it.isHiddenCell } == cells.count { it.isNotMineCell() }
    }

    companion object {

        fun of(positions: Positions, minePositions: Positions): Cells {
            val cells = Cells(positions.map { Cell.of(it) })
            val mineCells = Cells(minePositions.map { Cell.of(it, true) })
            return Cells(cells.inputMineCell(mineCells))
        }

        private fun Cells.inputMineCell(mineCells: Cells) =
            this.map { cell ->
                (mineCells.firstOrNull { it.position == cell.position } ?: cell)
                    .apply {
                        this.countingAdjacentMines(mineCells)
                    }
            }

        private const val NOT_FOUND_CELL = "해당 셀을 찾을 수 없습니다."
        private const val BLANK_COUNT = 0
    }
}
