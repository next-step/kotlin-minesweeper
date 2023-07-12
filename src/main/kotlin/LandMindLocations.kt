data class LandMindLocations(
    val points: List<Point>
) {
    fun contains(point: Point): Boolean {
        return points.contains(point)
    }
}