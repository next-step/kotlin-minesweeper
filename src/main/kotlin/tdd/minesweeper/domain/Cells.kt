package tdd.minesweeper.domain

@JvmInline
value class Cells(private val values: List<Cell>) : List<Cell> by values
