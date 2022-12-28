package minesweeper.domain

private const val MINE_SIGN = "*"

class MineCell(
    override val position: Position,
) : Cell(position, MINE_SIGN)
