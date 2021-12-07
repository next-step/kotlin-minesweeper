package minesweeper.domain.cell

import minesweeper.domain.position.Position

class NormalCell(position: Position) : Cell(position) {

    override fun isOpenedMineCell(): Boolean = false

    override fun isNotMineCell(): Boolean = true

    override fun getCellAdjacentCount(): Int = cellValue.value

    override fun isContainsAdjacentPositions(otherPosition: Position): Boolean =
        this.position.containsAdjacentPositions(otherPosition)

    override fun countingAdjacentMines(mineCells: Cells) {
        cellValue = CellValue(position.countingAdjacentMines(mineCells))
    }
}
