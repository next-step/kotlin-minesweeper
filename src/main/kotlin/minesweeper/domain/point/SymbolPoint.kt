package minesweeper.domain.point

import minesweeper.domain.SymbolType

data class SymbolPoint(
    val point: Point,
    private var symbol: SymbolType,
    private var marked: Boolean = false
) : Markable {
    constructor(x: Int, y: Int, symbol: SymbolType, marked: Boolean = false) : this(Point(x, y), symbol, marked)

    val x: Int
        get() = point.x

    val y: Int
        get() = point.y

    fun getSymbol(): SymbolType = when {
        hasSymbolType(SymbolType.BOUNDARY) -> SymbolType.BOUNDARY
        marked -> symbol
        else -> SymbolType.BLIND
    }

    override fun isMarked(): Boolean = marked

    override fun marking(): Boolean {
        return if (!marked && isMarkableSymbol()) {
            marked = true
            true
        } else {
            false
        }
    }

    fun updateSymbol(mineCount: Int) {
        if (symbol.isUpdatable()) {
            symbol = SymbolType.from(mineCount)
        }
    }

    fun isMarkableSymbol(): Boolean = symbol.isMarkable()

    fun hasSymbolType(other: SymbolType): Boolean = symbol == other

    companion object {
        fun createBoundaryPoint(point: Point): SymbolPoint = SymbolPoint(point, SymbolType.BOUNDARY)
    }
}
