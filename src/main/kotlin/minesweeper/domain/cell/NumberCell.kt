package minesweeper.domain.cell

class NumberCell(override val location: Location) : Cell {
    override val symbol: Symbol
        get() = Symbol.ZERO
}
