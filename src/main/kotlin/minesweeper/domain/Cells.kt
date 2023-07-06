package minesweeper.domain

@JvmInline
value class Cells(val values: List<Cell>) : List<Cell> by values
