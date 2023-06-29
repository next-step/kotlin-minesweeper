package tdd.minesweeper.domain

import tdd.minesweeper.domain.type.AdjacentPoints
import tdd.minesweeper.domain.type.SymbolType

typealias symbolSearchFilter = (SymbolPoint) -> Boolean
class Rows(values: List<Row>) {
    private val cacheMap: Map<Point, SymbolPoint> = values.flatten().associateBy(SymbolPoint::point)

    init {
        cacheMap.values.forEach(::updateSymbol)
    }

    private fun updateSymbol(symbolPoint: SymbolPoint) {
        AdjacentPoints.values().map { it.moving(symbolPoint.point) }.count {
            cacheMap[it]?.equalsSymbol(SymbolType.MINE) ?: false
        }.also {
            symbolPoint.updateSymbol(SymbolType.from(it))
        }
    }


    fun findByPoint(point: Point): SymbolPoint =
        cacheMap[point] ?: throw IllegalArgumentException("해당 위치에 정보가 존재하지 않습니다. Input: $point")

    fun findByFilter(filter: symbolSearchFilter): List<SymbolPoint> =
        cacheMap.values.filter(filter)

    operator fun contains(point: Point): Boolean = cacheMap.containsKey(point)
}
