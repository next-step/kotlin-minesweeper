package minesweeper.domain

data class BasicCell(private val location: Location) : Cell {
    constructor(row: Int, column: Int) : this(Location(row, column))

    override fun location(): Location = location.copy()

    override fun display(): String = CLOSED_SYMBOL

    companion object {
        private const val CLOSED_SYMBOL = "■"
    }
}
