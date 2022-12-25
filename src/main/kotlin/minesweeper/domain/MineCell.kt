package minesweeper.domain

@JvmInline
value class MineCell(override val state: String = "*") : Cell
