package minesweeper.domain.cell

data class BasicCell(
    override val location: Location,
    val numberOfAdjacentMines: NumberOfAdjacentMines = NumberOfAdjacentMines.ZERO,
) : Cell {
    override val symbol: Symbol
        get() = Symbol.from(numberOfAdjacentMines)

    constructor(row: Int, column: Int) : this(Location(row, column))

    fun withIncrementedNumberOfAdjacentMines() = copy(numberOfAdjacentMines = numberOfAdjacentMines.inc())
}
