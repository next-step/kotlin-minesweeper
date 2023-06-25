package minesweeper.domain

class MinePoint(
    x: Int,
    y: Int,
    symbol: SymbolType
) : Point(x = x, y = y) {

    var symbol: SymbolType = symbol
        private set

    fun updateNextSymbol() {
        symbol = symbol.getNextOrCurrent()
    }
}
