package minesweeper.domain.cell

import minesweeper.domain.position.Position

interface Cell {

    val position: Position
    val hiddenState: HiddenState
    val cellValue: CellValue

    fun position(): Position = position

    fun isHiddenCell(): Boolean = hiddenState.isHidden

    fun openCell() {
        hiddenState.changeVisible()
    }

    fun isOpenedMineCell(): Boolean
    fun isNotMineCell(): Boolean
    fun getCellAdjacentCount(): Int
    fun isContainsAdjacentPositions(otherPosition: Position): Boolean
    fun countingAdjacentMines(mineCells: Cells)

    companion object {
        fun of(position: Position, isMineCell: Boolean = false): Cell =
            when (isMineCell) {
                true -> MineCell(position)
                false -> NormalCell(position)
            }
    }
}
