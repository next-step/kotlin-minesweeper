package domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun getSurroundingCoordinates(minX: Int = 1, minY: Int = 1, maxX: Int, maxY: Int): Set<Coordinate> {
        val surroundingAllCoordinates = setOf(
            Coordinate(x, y - 1),
            Coordinate(x, y + 1),
            Coordinate(x - 1, y),
            Coordinate(x + 1, y),
            Coordinate(x - 1, y - 1),
            Coordinate(x - 1, y + 1),
            Coordinate(x + 1, y + 1),
            Coordinate(x + 1, y - 1)
        )

        return surroundingAllCoordinates
            .filter { it.x in (minX..maxX) }
            .filter { it.y in (minY..maxY) }
            .toSet()
    }

    fun getFourWayCoordinates(minX: Int = 1, minY: Int = 1, maxX: Int, maxY: Int): Set<Coordinate> {
        val fourWayCoordinates = setOf(
            Coordinate(x, y - 1),
            Coordinate(x, y + 1),
            Coordinate(x - 1, y),
            Coordinate(x + 1, y)
        )

        return fourWayCoordinates
            .filter { it.x in (minX..maxX) }
            .filter { it.y in (minY..maxY) }
            .toSet()
    }

    override fun compareTo(other: Coordinate): Int {
        return if (this.y == other.y) this.x.compareTo(other.x) else this.y.compareTo(other.y)
    }
}
