package minesweeper.domain

class SymbolPoint(
    x: Int,
    y: Int,
    symbol: SymbolType
) {
    private val point: Point = Point(x = x, y = y)
    var symbol: SymbolType = symbol
        private set

    val x: Int
        get() = point.x

    val y: Int
        get() = point.y

    fun updateSymbol(mineCount: Int) {
        if (symbol.isUpdatable()) {
            symbol = SymbolType.from(mineCount)
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is SymbolPoint) {
            this.point == other.point && this.symbol == other.symbol
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = point.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }
}
