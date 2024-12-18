package minesweeper.domain.cell

data class ClosedCell(
    override val location: Location,
    override val hasLandmine: Boolean = false,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines = NumberOfAdjacentMines.ZERO,
) : Cell, HasLandmine, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.CLOSED

    fun open(): Cell {
        if (hasLandmine) {
            return LandmineCell(location)
        }
        return NumberCell(location, numberOfAdjacentLandmines)
    }

    fun withNumberOfAdjacentLandmines(newNumberOfAdjacentMines: NumberOfAdjacentMines): ClosedCell {
        return ClosedCell(location, hasLandmine, newNumberOfAdjacentMines)
    }

    fun plantMine(): ClosedCell = ClosedCell(location = location, numberOfAdjacentLandmines = numberOfAdjacentLandmines, hasLandmine = true)
}
