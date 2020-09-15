package minesweeper.domain.squarestate

class Mine(override val mineCount: Int = 0) : SquareState {
    override val isMine: Boolean = true

    override fun toString(): String = SquareSymbol.MINE.symbol
}
