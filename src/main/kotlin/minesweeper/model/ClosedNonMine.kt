package minesweeper.model

class ClosedNonMine(
    position: CellPosition,
    override val isOpened: Boolean = false,
    override val isMine: Boolean = false,
) : Cell(position) {

    override fun open(): Cell = OpenedNonMine(position)
}
