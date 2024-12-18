package minesweeper.domain.cell

class NumberCell(
    override val location: Location,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines,
) : Cell, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.from(numberOfAdjacentLandmines)
}
