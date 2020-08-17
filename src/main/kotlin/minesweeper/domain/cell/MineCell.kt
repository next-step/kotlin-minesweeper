package minesweeper.domain.cell

class MineCell(
    position: Position
) : Cell(position) {

    override fun isMine() = true

    override fun toString() = "*"
}

fun Position.toMineCell() = MineCell(this)
