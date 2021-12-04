package minesweeper.domain.cell

import minesweeper.domain.position.Position

data class NormalCell(
    val position: Position,
    private var isHidden: Boolean = true,
) : Cell {

    private lateinit var cellValue: CellValue

    override fun position(): Position = position

    override fun isHiddenCell(): Boolean = isHidden

    override fun openCell() {
        isHidden = false
    }

    override fun getCellAdjacentCount(): Int = cellValue.value

    override fun isContainsAdjacentPositions(otherPosition: Position): Boolean =
        this.position.containsAdjacentPositions(otherPosition)

    override fun countingAdjacentMines(mineCells: Cells) {
        cellValue = CellValue(position.countingAdjacentMines(mineCells))
    }
}
