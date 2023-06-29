package minesweeper.domain

import minesweeper.domain.point.Point
import minesweeper.domain.point.SymbolPoint
import java.util.function.Consumer

class Rows(private val values: List<Row>) : List<Row> by values {

    val realSize: Int = size - BOUNDARY_SIZE

    override fun forEach(action: Consumer<in Row>?) {
        requireNotNull(action)
        (1 until values.size).forEach {
            action.accept(values[it])
        }
    }

    private val cachedStore: Map<Point, SymbolPoint> = values.flatten()
        .filterNot { it.hasSymbolType(SymbolType.BOUNDARY) }
        .associateBy(SymbolPoint::point)

    operator fun contains(point: Point): Boolean = cachedStore.containsKey(point)

    fun findPoint(point: Point): SymbolPoint = cachedStore[point]
        ?: throw IndexOutOfBoundsException("적절한 좌표가 아닙니다. [line size:${this.size}, input index: ${point.y}]")

    companion object {
        private const val BOUNDARY_SIZE = 2
    }
}
