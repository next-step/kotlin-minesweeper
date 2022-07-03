package minesweeper.model2

import minesweeper.model.CellPosition

abstract class Cell(
    val position: CellPosition,
) {
    abstract val isOpened: Boolean

    abstract val isMine: Boolean

    fun isMineAndIn(positions: Set<CellPosition>) = isMine && isIn(positions)

    fun isClosedAndIn(positions: Set<CellPosition>) = !isOpened && isIn(positions)

    private fun isIn(positions: Collection<CellPosition>) = positions.contains(position)

    abstract fun open(): Cell
}
