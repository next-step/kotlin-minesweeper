package minesweeper.domain

import minesweeper.domain.finder.AdjacentPoints
import minesweeper.domain.point.Point
import minesweeper.domain.point.SymbolPoint
import minesweeper.domain.state.ResultState

typealias adjacentFilter = (SymbolPoint) -> Boolean

class MineBoard(
    val area: Area,
    val rows: Rows,
) {
    constructor(width: Int, height: Int, rows: Rows) : this(Area(width, height), rows)

    private val remainPointCount = RemainPointCount(area.size)

    init {
        val flattenSymbols = rows.flatten().filterNot { it.hasSymbolType(SymbolType.BOUNDARY) }
        flattenSymbols.forEach(::updateSymbol)
        remainPointCount.decreaseAndGet(flattenSymbols.count { !it.isMarkableSymbol() })
    }

    private fun updateSymbol(symbolPoint: SymbolPoint) =
        getAdjacentPointsByFilter(symbolPoint)
            .count { it.hasSymbolType(SymbolType.MINE) }
            .run(symbolPoint::updateSymbol)

    private fun getAdjacentPointsByFilter(
        symbolPoint: SymbolPoint,
        filter: adjacentFilter = { true }
    ): List<SymbolPoint> =
        findPoint(point = symbolPoint.point)
            .let(::getAdjacentPointsOf)
            .filter(filter)

    private fun getAdjacentPointsOf(point: SymbolPoint): List<SymbolPoint> =
        AdjacentPoints.values().map { it.movePoint(point) }
            .filter { it in rows }
            .map(::findPoint)

    fun findPoint(point: Point): SymbolPoint = rows.findPoint(point)

    fun marking(targetPoint: Point): ResultState {
        val foundPoint = findPoint(targetPoint)
        if (foundPoint.hasSymbolType(SymbolType.MINE)) {
            return ResultState.LOSE
        }

        foundPoint.marking().takeIf { it }
            ?.run {
                remainPointCount.decreaseAndGet()
                revealAdjacentPoints(foundPoint)
            }

        return if (remainPointCount.isZero()) {
            ResultState.WIN
        } else {
            ResultState.CONTINUABLE
        }
    }

    private fun revealAdjacentPoints(point: SymbolPoint) {
        if (!point.hasSymbolType(SymbolType.ZERO)) {
            return
        }

        val adjacentPoints = getAdjacentPointsByFilter(point) {
            !it.isMarked() && it.isMarkableSymbol()
        }

        adjacentPoints.forEach { adjacentPoint ->
            markingAdjacentPoint(adjacentPoint)
        }
    }

    private fun markingAdjacentPoint(adjacentPoint: SymbolPoint) {
        if (adjacentPoint.hasSymbolType(SymbolType.ZERO)) {
            marking(adjacentPoint.point)
        } else {
            adjacentPoint.marking()
                .takeIf { it }
                ?.run { remainPointCount.decreaseAndGet() }
        }
    }
}
