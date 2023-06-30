package tdd.minesweeper.domain

import tdd.minesweeper.domain.type.SymbolType

data class SymbolPoint(
    val point: Point,
    private var symbol: SymbolType,
    private var marked: Boolean = false
) {
    constructor(x: Int, y: Int, symbol: SymbolType) : this(Point(x, y), symbol)

    val isMarked: Boolean
        get() = marked

    fun equalsSymbol(other: SymbolType): Boolean = symbol == other

    fun updateSymbol(other: SymbolType) {
        if (symbol != SymbolType.MINE) {
            symbol = other
        }
    }

    fun isMarkable(): Boolean = !marked && symbol.isMarkable()

    fun mark(): Boolean {
        if (isMarkable()) {
            marked = true
            return marked
        }

        return false
    }

    fun getSymbol(): SymbolType = when {
        !marked -> SymbolType.BLIND
        else -> symbol
    }
}
