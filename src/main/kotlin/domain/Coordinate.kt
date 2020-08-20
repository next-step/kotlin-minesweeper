package domain

import kotlin.math.max
import kotlin.math.min

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun findNotifyRange(width: Int, height: Int): List<Coordinate> {
        return Direction.values().map { this + it }.filter { inRange(it, width, height) }
    }

    fun applyDirection(direction: Direction, width: Int, height: Int): Coordinate {
        val applyResult = this + direction
        return Coordinate(
            max(0, min(applyResult.x, width - 1)),
            max(0, min(applyResult.y, height - 1))
        )
    }

    override fun compareTo(other: Coordinate): Int {
        val result = x.compareTo(other.x)
        if (result == 0) {
            return y.compareTo(other.y)
        }
        return result
    }

    operator fun plus(other: Direction) = Coordinate(this.x + other.distanceX, this.y + other.distanceY)

    private fun inRange(coordinate: Coordinate, width: Int, height: Int) =
        (coordinate.x in 0 until width) && (coordinate.y in 0 until height)

    companion object {
        private const val NOTIFY_RANGE = 1
    }
}
