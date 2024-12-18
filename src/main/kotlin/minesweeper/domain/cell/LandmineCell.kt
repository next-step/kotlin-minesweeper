package minesweeper.domain.cell

data class LandmineCell(override val location: Location) : Cell, HasLandmine {
    override val symbol: Symbol
        get() = Symbol.LANDMINE
    override val hasLandmine: Boolean
        get() = true
}
