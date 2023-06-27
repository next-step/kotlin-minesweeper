package minesweeper.domain

data class SymbolPoint(
    private val point: Point,
    private var symbol: SymbolType,
    private var marked: Boolean = false
) {
    constructor(x: Int, y: Int, symbol: SymbolType, marked: Boolean = false) : this(Point(x, y), symbol, marked)

    val x: Int
        get() = point.x

    val y: Int
        get() = point.y

    fun getSymbol(): SymbolType =
        if (marked) symbol
        else SymbolType.BLIND

    fun isMarked(): Boolean = marked

    fun marking() {
        marked = true
    }

    fun updateSymbol(mineCount: Int) {
        if (symbol.isUpdatable()) {
            symbol = SymbolType.from(mineCount)
        }
    }

    fun equalsTo(otherSymbol: SymbolType): Boolean = symbol == otherSymbol
}
