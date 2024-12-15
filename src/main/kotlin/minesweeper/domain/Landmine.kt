package minesweeper.domain

data class Landmine(private val location: Location) : Cell {
    constructor(row: Int, column: Int) : this(Location(row, column))

    override fun location(): Location = location.copy()

    override fun display(): String = LANDMINE_SYMBOL

    companion object {
        private const val LANDMINE_SYMBOL = "*"
    }
}
