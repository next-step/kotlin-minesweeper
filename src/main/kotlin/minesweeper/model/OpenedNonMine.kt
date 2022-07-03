package minesweeper.model

class OpenedNonMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = false,
) : Cell(position) {

    override fun open(): Cell = this
}
