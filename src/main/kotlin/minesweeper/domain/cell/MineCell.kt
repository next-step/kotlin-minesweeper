package minesweeper.domain.cell

class MineCell(
    position: Position
) : Cell(position) {

    override fun isMine() = true

    override fun toString() = "\uD83D\uDCA3"
}

fun Position.toMineCell() = MineCell(this)
