package minesweeper.domain.cell

data class NumberCell(
    override val location: Location,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines,
) : Cell, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.from(numberOfAdjacentLandmines)
}
