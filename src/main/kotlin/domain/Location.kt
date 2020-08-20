package domain

import kotlin.math.max
import kotlin.math.min

data class Location(val x: Int, val y: Int) : Comparable<Location> {

    fun findNotifyRange(width: Int, height: Int) =
        Direction.values().map { this + it }.filter { inRange(it, width, height) }

    fun applyDirection(direction: Direction, width: Int, height: Int) =
        with(this + direction) {
            Location(
                max(0, min(this.x, width - 1)),
                max(0, min(this.y, height - 1))
            )
        }

    override fun compareTo(other: Location): Int {
        val result = x.compareTo(other.x)
        if (result == 0) {
            return y.compareTo(other.y)
        }
        return result
    }

    operator fun plus(other: Direction) = Location(this.x + other.distanceX, this.y + other.distanceY)

    private fun inRange(location: Location, width: Int, height: Int) =
        (location.x in 0 until width) && (location.y in 0 until height)

    companion object {
        private const val NOTIFY_RANGE = 1
        private const val DELIMITER = ","
        private const val DIMENSION = 2

        fun String?.toLocationOrNull(): Location? {
            val parsedLocation = parseLocation() ?: return null
            if (parsedLocation.size == DIMENSION) {
                return Location(parsedLocation[0].toInt(), parsedLocation[1].toInt())
            }
            return null
        }

        private fun String?.parseLocation() = this?.split(DELIMITER)
            ?.map { it.trim() }
            ?.filter { it.toIntOrNull() != null }
    }
}
