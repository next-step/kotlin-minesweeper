package minesweeper.domain.cell

class ClosedCell(
    override val location: Location,
    val hasLandmine: Boolean = false,
) : Cell {
    fun open(): Cell {
        if (hasLandmine) {
            return LandmineCell(location)
        }
        return NumberCell(location)
    }

    override val symbol: Symbol
        get() = Symbol.CLOSED
}
