package minesweeper.ui

import minesweeper.domain.MineBoard
import minesweeper.domain.SymbolPoint
import minesweeper.domain.SymbolType

sealed class MineSweeperOutput {
    abstract fun printMineBoard(mineBoard: MineBoard)

    protected fun convertToSymbol(point: SymbolPoint): String = when (point.symbol) {
        SymbolType.BLIND -> "C"
        SymbolType.ZERO -> "0"
        SymbolType.MINE -> "*"
        SymbolType.ONE -> "1"
        SymbolType.TWO -> "2"
        SymbolType.THREE -> "3"
        SymbolType.FOUR -> "4"
        SymbolType.FIVE -> "5"
        SymbolType.SIX -> "6"
        SymbolType.SEVEN -> "7"
        SymbolType.EIGHT -> "8"
    }
}
