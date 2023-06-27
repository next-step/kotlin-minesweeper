package minesweeper.domain

import minesweeper.domain.finder.AdjacentPoints
import minesweeper.domain.state.ResultState

class MineBoard(
    val area: Area,
    val lines: Lines,
) {
    constructor(width: Int, height: Int, lines: Lines) : this(Area(width, height), lines)

    var remainPointCount = area.size
        private set

    init {
        val flattenSymbols = lines.flatten()
        flattenSymbols.forEach(::updateSymbol)
        remainPointCount -= flattenSymbols.count { it.equalsTo(SymbolType.MINE) }
    }

    private fun updateSymbol(symbolPoint: SymbolPoint) =
        getAdjacentPoints(symbolPoint)
            .count { it.equalsTo(SymbolType.MINE) }
            .run(symbolPoint::updateSymbol)

    private fun getAdjacentPoints(symbolPoint: SymbolPoint): List<SymbolPoint> =
        findPoint(point = symbolPoint.point).let(::getAdjacentPointsOf)

    private fun getAdjacentPointsOf(point: SymbolPoint): List<SymbolPoint> =
        AdjacentPoints.values().map { it.movePoint(point) }
            .filter { it in lines }
            .map(::findPoint)

    fun findPoint(point: Point): SymbolPoint = lines.findPoint(point)

    fun marking(targetPoint: Point): ResultState {
        val foundPoint = findPoint(targetPoint)
        if (foundPoint.equalsTo(SymbolType.MINE)) {
            return ResultState.LOSE
        }

        foundPoint.marking().takeIf { it }?.run {
            revealAdjacentPoints(foundPoint)
        }

        return if (remainPointCount == 0) {
            ResultState.WIN
        } else {
            ResultState.CONTINUABLE
        }
    }

    private fun revealAdjacentPoints(point: SymbolPoint) {
        remainPointCount--
        getAdjacentPoints(point).filter { !it.isMarked() && !it.equalsTo(SymbolType.MINE) }
            .forEach {
                remainPointCount--
                it.marking()
            }
    }
}
