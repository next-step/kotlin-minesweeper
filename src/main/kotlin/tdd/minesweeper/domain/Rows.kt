package tdd.minesweeper.domain

typealias symbolSearchFilter = (SymbolPoint) -> Boolean
class Rows(private val values: List<Row>) {

    private val cacheMap: Map<Point, SymbolPoint> = values.flatten().associateBy(SymbolPoint::point)

    fun findByPoint(point: Point): SymbolPoint =
        cacheMap[point] ?: throw IllegalArgumentException("해당 위치에 정보가 존재하지 않습니다. Input: $point")

    fun findByFilter(filter: symbolSearchFilter): List<SymbolPoint> =
        cacheMap.values.filter(filter)
}
