package minesweeper.domain

data class BasicCell(
    private val location: Location,
    val numberOfAdjacentMines: NumberOfAdjacentMines = NumberOfAdjacentMines.ZERO,
) : Cell {
    constructor(row: Int, column: Int) : this(Location(row, column))

    override fun location(): Location = location.copy()

    override fun symbol(): Symbol = Symbol.from(numberOfAdjacentMines)

    fun withIncrementedNumberOfAdjacentMines() = copy(numberOfAdjacentMines = numberOfAdjacentMines.inc())
}
