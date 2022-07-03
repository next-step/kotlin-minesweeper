package minesweeper.model2

import minesweeper.model.CellPosition

class ClosedMine(
    position: CellPosition,
    override val isOpened: Boolean = false,
    override val isMine: Boolean = true,
) : Cell(position) {

    override fun open(): Cell = OpenedMine(position)
}
