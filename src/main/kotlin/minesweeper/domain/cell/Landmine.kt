package minesweeper.domain.cell

data class Landmine(private val location: Location) : Cell {
    constructor(row: Int, column: Int) : this(Location(row, column))

    override fun location(): Location = location.copy()

    override fun symbol(): Symbol = Symbol.LANDMINE
}
