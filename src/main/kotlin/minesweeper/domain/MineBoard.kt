package minesweeper.domain

import minesweeper.domain.finder.AdjacentPoints

class MineBoard(
    val height: Int,
    val width: Int,
    val lines: Lines
) {

    init {
        lines.flatten().forEach(::updateSymbol)
    }

    private fun updateSymbol(symbolPoint: SymbolPoint) =
        getAdjacentPoints(symbolPoint)
            .count { it.symbol == SymbolType.MINE }
            .run(symbolPoint::updateSymbol)

    private fun getAdjacentPoints(point: SymbolPoint): List<SymbolPoint> =
        findPoint(x = point.x, y = point.y).let(::getAdjacentPointsOf)

    private fun getAdjacentPointsOf(point: SymbolPoint): List<SymbolPoint> =
        AdjacentPoints.values().map { it.movePoint(point) }
            .filter { it in lines }
            .map { findPoint(x = it.x, y = it.y) }

    private fun findPoint(x: Int, y: Int): SymbolPoint = lines.findPoint(Point(x = x, y = y))
}
