package minesweeper.domain.cell

import minesweeper.domain.position.Position

data class MineCell(
    val position: Position,
    private var isHidden: Boolean = true,
) : Cell {

    private lateinit var cellValue: CellValue

    override fun position(): Position = position

    override fun isHiddenCell(): Boolean = isHidden

    override fun openCell() {
        isHidden = false
    }

    override fun getCellAdjacentCount(): Int = MINE_VALUE

    override fun isContainsAdjacentPositions(otherPosition: Position): Boolean = false

    override fun countingAdjacentMines(mineCells: Cells) {
        cellValue = CellValue(MINE_VALUE)
    }

    companion object {
        private const val MINE_VALUE = -1
    }
}
