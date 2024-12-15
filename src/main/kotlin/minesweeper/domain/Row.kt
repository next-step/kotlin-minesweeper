package minesweeper.domain

data class Row(private val cells: List<Cell>) {
    fun display(): String = cells.joinToString(separator = " ") { it.display() }

    fun cells(): List<Cell> = cells.toList()
}
