package domain

data class Location(val x: Int, val y: Int) : Comparable<Location> {

    override fun compareTo(other: Location): Int {
        val result = x.compareTo(other.x)
        if (result == 0) {
            return y.compareTo(other.y)
        }
        return result
    }

    operator fun plus(other: Direction) = Location(this.x + other.distanceX, this.y + other.distanceY)

    override fun toString() = "($x, $y)"

    companion object {
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
