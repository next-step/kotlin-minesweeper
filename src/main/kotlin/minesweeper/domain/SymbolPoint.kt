package minesweeper.domain

data class SymbolPoint(
    private val point: Point,
    private var symbol: SymbolType
) {
    constructor(x: Int, y: Int, symbol: SymbolType) : this(Point(x, y), symbol)

    val x: Int
        get() = point.x

    val y: Int
        get() = point.y

    fun getSymbol(): SymbolType = symbol

    fun updateSymbol(mineCount: Int) {
        if (symbol.isUpdatable()) {
            symbol = SymbolType.from(mineCount)
        }
    }
}
