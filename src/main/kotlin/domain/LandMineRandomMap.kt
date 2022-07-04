package domain

class LandMineRandomMap(
    private val xCoordinatePoints: List<CoordinatePoint>,
    private val yCoordinatePoints: List<CoordinatePoint>,
    coordinateGenerator: CoordinateGenerator
) {

    val landMineCoordinates = coordinateGenerator.coordinates()
    val mapLine = yCoordinatePoints.map { y ->
        Line(xCoordinatePoints.map { x -> landMineCount(Coordinate(x, y), landMineCoordinates) })
    }

    private fun landMineCount(
        coordinate: Coordinate,
        landMineCoordinates: List<Coordinate>
    ): Int {

        if (landMineCoordinates.contains(coordinate)) return LAND_MINE_FEATURE

        var sum = 0
        for (coordinateDirection in CoordinateDirection.values()) {
            val x = coordinate.x + coordinateDirection.x
            val y = coordinate.y + coordinateDirection.y
            if (!(x <= 0 || x > xCoordinatePoints.size || y <= 0 || y > yCoordinatePoints.size) &&
                landMineCoordinates.contains(Coordinate(x, y))
            ) sum += 1
        }

        return sum
    }

    companion object {
        const val LAND_MINE_FEATURE = -1
    }
}
