package minesweeper.model

class OpenedMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = true,
) : Cell(position) {

    override fun open(): Cell = this
}
