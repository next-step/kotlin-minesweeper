package domain

data class Location(val x: Int, val y: Int) : Comparable<Location> {

    override fun compareTo(other: Location): Int {
        val result = y.compareTo(other.y)
        if (result == 0) {
            return x.compareTo(other.x)
        }
        return result
    }

    operator fun plus(other: Direction) = Location(this.x + other.distanceX, this.y + other.distanceY)

    override fun toString() = "($x, $y)"

    companion object {
        private const val DELIMITER = ","
        private const val DIMENSION = 2

        fun String?.toLocationOrNull(): Location? = this?.run {
            val parsedLocation = parseLocation()
            if (parsedLocation.size == DIMENSION) {
                return Location(parsedLocation[0], parsedLocation[1])
            }
            return null
        }

        private fun String.parseLocation() = this.split(DELIMITER)
            .map { it.trim() }
            .mapNotNull { it.toIntOrNull() }
    }
}
