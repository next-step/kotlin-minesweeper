data class LandMineLocations(
    val points: List<Point>
) {
    constructor(vararg point: Point) : this(points = point.toList())

    fun contains(point: Point): Boolean {
        return points.contains(point)
    }
}