package minesweeper.domain

private const val MINE_SIGN = "*"

@JvmInline
value class MineCell(override val state: String = MINE_SIGN) : Cell
