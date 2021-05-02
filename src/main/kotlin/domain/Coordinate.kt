package domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun getSurroundingCoordinates(minX: Int = 1, minY: Int = 1, maxX: Int, maxY: Int): Set<Coordinate> {
        val surroundingAllCoordinates = CoordinateDirection.getSurroundingAllCoordinates(x, y)

        return surroundingAllCoordinates
            .filter { it.x in (minX..maxX) }
            .filter { it.y in (minY..maxY) }
            .toSet()
    }

    fun getFourWayCoordinates(minX: Int = 1, minY: Int = 1, maxX: Int, maxY: Int): Set<Coordinate> {
        val fourWayCoordinates = CoordinateDirection.getFourWayCoordinates(x, y)

        return fourWayCoordinates
            .filter { it.x in (minX..maxX) }
            .filter { it.y in (minY..maxY) }
            .toSet()
    }

    override fun compareTo(other: Coordinate): Int {
        return if (this.y == other.y) this.x.compareTo(other.x) else this.y.compareTo(other.y)
    }
}
