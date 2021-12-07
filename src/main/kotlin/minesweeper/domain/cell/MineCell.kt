package minesweeper.domain.cell

import minesweeper.domain.position.Position

class MineCell(position: Position) : Cell(position) {

    override fun isOpenedMineCell(): Boolean = !isHiddenCell

    override fun isNotMineCell(): Boolean = false

    override fun getCellAdjacentCount(): Int = MINE_VALUE

    override fun isContainsAdjacentPositions(otherPosition: Position): Boolean = false

    override fun countingAdjacentMines(mineCells: Cells) {
        cellValue = CellValue(MINE_VALUE)
    }

    companion object {
        private const val MINE_VALUE = -1
    }
}
