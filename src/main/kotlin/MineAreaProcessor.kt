object MineAreaProcessor {
    val POINT_LIST = listOf(
        Point(0, 1),
        Point(1, 1),
        Point(1, 0),
        Point(1, -1),
        Point(0, -1),
        Point(-1, -1),
        Point(-1, 0),
        Point(-1, 1)
    )

    fun updateSurroundingMineCounts(
        mineCountMatrix: List<List<Int>>,
        height: Int,
        width: Int,
        heightRange: IntRange,
        widthRange: IntRange,
    ): List<List<Int>> {
        var result = mineCountMatrix.map(List<Int>::toMutableList).toMutableList()
        result[height][width] = -1
        for (point in POINT_LIST) {
            if (height + point.x !in heightRange || width + point.y !in widthRange) continue
            if (result[height + point.x][width + point.y] == -1) continue
            result[height + point.x][width + point.y] += 1
        }
        return result.toList()
    }
}
