package minesweeper.domain.cell

import minesweeper.domain.position.Position

interface Cell {

    fun isOpenedMineCell(): Boolean
    fun isNotMineCell(): Boolean
    fun position(): Position
    fun isHiddenCell(): Boolean
    fun openCell()
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
