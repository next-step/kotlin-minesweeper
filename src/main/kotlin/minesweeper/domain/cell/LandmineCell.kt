package minesweeper.domain.cell

data class LandmineCell(override val location: Location) : Cell {
    override val symbol: Symbol
        get() = Symbol.LANDMINE
}
