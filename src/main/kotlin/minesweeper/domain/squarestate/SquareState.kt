package minesweeper.domain.squarestate

interface SquareState {
    val isMine: Boolean
    val mineCount: Int

    fun isBoundary(): Boolean = this.toString() == SquareSymbol.BOUNDARY.symbol

    fun updateMineCount(count: Int): SquareState = this

    override fun toString(): String
}

enum class SquareSymbol(val symbol: String) {
    BOUNDARY(""),
    MINE("✴")
}
