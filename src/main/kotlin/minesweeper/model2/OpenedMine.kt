package minesweeper.model2

import minesweeper.model.CellPosition

class OpenedMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = true,
) : Cell(position) {

    override fun open(): Cell = this
}
