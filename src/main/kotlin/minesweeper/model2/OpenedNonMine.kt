package minesweeper.model2

import minesweeper.model.CellPosition

class OpenedNonMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = false,
) : Cell(position) {

    override fun open(): Cell = this
}
