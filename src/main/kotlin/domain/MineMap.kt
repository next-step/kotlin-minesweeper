package domain

import domain.field.Point
import domain.map.ArrayMap

class MineMap(private val map: ArrayMap) {

    private val delta = listOf(
        Point(-1, -1),
        Point(0, -1),
        Point(1, -1),
        Point(-1, 0),
        Point(1, 0),
        Point(-1, 1),
        Point(0, 1),
        Point(1, 1)
    )

    fun resultMineStatus(point: Point): String {
        val nearMineCount = nearMineCount(point)
        return map.getPoint(point).spotSymbol(nearMineCount)
    }

    fun getHeight(): Int = map.height

    fun getWidth(): Int = map.width

    private fun nearMineCount(point: Point): Int =
        delta.map {
            map.getPointOrNull(point + it)
        }.count {
            it != null && it.isMine()
        }
}
