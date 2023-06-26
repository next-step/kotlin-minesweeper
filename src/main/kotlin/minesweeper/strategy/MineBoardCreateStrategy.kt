package minesweeper.strategy

import minesweeper.domain.Line
import minesweeper.domain.Lines
import minesweeper.domain.MineBoard
import minesweeper.domain.SymbolPoint
import minesweeper.domain.SymbolType
import minesweeper.request.MinesCreateRequest

abstract class MineBoardCreateStrategy {
    abstract fun create(request: MinesCreateRequest): MineBoard

    protected fun convertToLines(symbols: List<SymbolType>, request: MinesCreateRequest): Lines =
        symbols.chunked(request.width)
            .mapIndexed(::convertToLine)
            .let(::Lines)

    private fun convertToLine(y: Int, line: List<SymbolType>): Line =
        line.mapIndexed { x, symbol ->
            SymbolPoint(x = x, y = y, symbol = symbol)
        }.let(::Line)
}
