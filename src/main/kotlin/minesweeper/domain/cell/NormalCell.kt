package minesweeper.domain.cell

import minesweeper.domain.position.Position

class NormalCell(override val position: Position, override val hiddenState: HiddenState = HiddenState()) : Cell {

    override lateinit var cellValue: CellValue
        private set

    override fun isOpenedMineCell(): Boolean = false

    override fun isNotMineCell(): Boolean = true

    override fun getCellAdjacentCount(): Int = cellValue.value

    override fun isContainsAdjacentPositions(otherPosition: Position): Boolean =
        this.position.containsAdjacentPositions(otherPosition)

    override fun countingAdjacentMines(mineCells: Cells) {
        cellValue = CellValue(position.countingAdjacentMines(mineCells))
    }
}
