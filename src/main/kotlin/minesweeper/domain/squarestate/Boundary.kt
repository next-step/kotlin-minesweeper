package minesweeper.domain.squarestate

class Boundary(override val mineCount: Int = 0) : SquareState {
    override val isMine: Boolean = false

    override fun toString(): String = SquareSymbol.BOUNDARY.symbol
}
