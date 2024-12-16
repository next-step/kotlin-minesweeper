package minesweeper.domain

data class BasicCell(private val location: Location) : Cell {
    constructor(row: Int, column: Int) : this(Location(row, column))

    override fun location(): Location = location.copy()

    override fun symbol(): Symbol = Symbol.CLOSED
}
