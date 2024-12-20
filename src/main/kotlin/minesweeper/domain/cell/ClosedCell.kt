package minesweeper.domain.cell

data class ClosedCell(
    override val location: Location,
    val hasLandmine: Boolean = false,
    override val numberOfAdjacentLandmines: NumberOfAdjacentMines = NumberOfAdjacentMines.ZERO,
) : Cell, HasAdjacentLandmines {
    override val symbol: Symbol
        get() = Symbol.CLOSED

    fun open(): Cell {
        if (hasLandmine) {
            return LandmineCell(location)
        }
        return NumberCell(location, numberOfAdjacentLandmines)
    }

    fun withNumberOfAdjacentLandmines(newNumberOfAdjacentMines: NumberOfAdjacentMines): ClosedCell {
        return this.copy(numberOfAdjacentLandmines = newNumberOfAdjacentMines)
    }

    fun plantMine(): ClosedCell = this.copy(hasLandmine = true)
}
