package minesweeper.domain.cell

import minesweeper.domain.position.Position

abstract class Cell(
    val position: Position
) {
    private val hiddenState: HiddenState = HiddenState()
    protected lateinit var cellValue: CellValue

    val isHiddenCell: Boolean
        get() = hiddenState.isHidden

    fun openCell() {
        hiddenState.changeVisible()
    }

    abstract fun isOpenedMineCell(): Boolean
    abstract fun isNotMineCell(): Boolean
    abstract fun getCellAdjacentCount(): Int
    abstract fun isContainsAdjacentPositions(otherPosition: Position): Boolean
    abstract fun countingAdjacentMines(mineCells: Cells)

    companion object {
        fun of(position: Position, isMineCell: Boolean = false): Cell =
            when (isMineCell) {
                true -> MineCell(position)
                false -> NormalCell(position)
            }
    }
}
