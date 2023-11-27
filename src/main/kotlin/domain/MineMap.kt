package domain

import domain.field.Point
import domain.map.ArrayMap
import domain.status.MineStatus

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

    fun resultMineStatus(point: Point): String =
        map.getPoint(point).spotSymbol()

    fun getHeight(): Int = map.height

    fun getWidth(): Int = map.width

    private fun nearMineCount(point: Point): Int =
        delta.map {
            map.getPointOrNull(point + it)
        }.count {
            it != null && it.isMine()
        }

    fun open(point: Point): MineStatus {
        val spot = map.getPointOrNull(point) ?: return MineStatus.EMPTY
        val nearMineCount = nearMineCount(point)
        val openStatus = spot.open(nearMineCount)
        if (nearMineCount == 0) {
            openNearSpot(point)
        }

        return openStatus
    }

    private fun openNearSpot(point: Point) {
        delta.forEach {
            val nextPoint = point + it
            val spot = map.getPointOrNull(nextPoint) ?: return@forEach
            if (spot.isOpen()) {
                return@forEach
            }
            open(nextPoint)
        }
    }

    fun noMoreOpenSpot(): Boolean =
        map.flatten().find { it.isOpen().not() && it.isMine().not() } == null
}
