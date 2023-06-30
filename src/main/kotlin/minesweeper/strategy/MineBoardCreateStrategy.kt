package minesweeper.strategy

import minesweeper.domain.MineBoard
import minesweeper.domain.Row
import minesweeper.domain.Rows
import minesweeper.domain.SymbolType
import minesweeper.domain.point.Point
import minesweeper.domain.point.SymbolPoint
import minesweeper.request.MinesCreateRequest

abstract class MineBoardCreateStrategy {
    abstract fun create(request: MinesCreateRequest): MineBoard

    protected fun convertToLines(symbols: List<SymbolType>, request: MinesCreateRequest): Rows =
        buildList {
            add(Row.createBoundary(size = request.width, height = 0))
            addAll(
                symbols.chunked(request.width)
                    .mapIndexed { index, symbolTypes ->
                        convertToLine(y = index + 1, line = symbolTypes)
                    }
                    .let(::Rows)
            )
            add(Row.createBoundary(size = request.width, height = request.height + 1))
        }.let(::Rows)

    private fun convertToLine(y: Int, line: List<SymbolType>): Row =
        buildList {
            add(SymbolPoint.createBoundaryPoint(Point(x = 0, y = y)))
            addAll(
                line.mapIndexed { x, symbol ->
                    SymbolPoint(x = x + 1, y = y, symbol = symbol)
                }
            )
            add(SymbolPoint.createBoundaryPoint(Point(x = line.size + 1, y = y)))
        }.let(::Row)
}
