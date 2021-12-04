package minesweeper.domain.cell

import minesweeper.domain.position.Position

interface Cell {
    fun isOpenedMineCell(): Boolean {
        if (this is MineCell) {
            return !isHiddenCell()
        }
        return false
    }

    fun isNotMineCell(): Boolean {
        return this is NormalCell
    }

    fun position(): Position
    fun isHiddenCell(): Boolean
    fun openCell()
    fun getCellAdjacentCount(): Int
    fun isContainsAdjacentPositions(otherPosition: Position): Boolean
    fun countingAdjacentMines(mineCells: Cells)

    companion object {
        fun of(position: Position): NormalCell = NormalCell(position)
    }
}