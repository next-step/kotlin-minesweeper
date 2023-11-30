package domain

class MineMap(
    mapInfo: MineMapInfo,
    private val mineMap: ArrayMap = RandomMineMap.newRandomMineMap(mapInfo.point, mapInfo.mineCount)
) {

    private val delta = listOf(
        Point(-1, -1), Point(-1, 0), Point(-1, 1),
        Point(0, -1), Point(0, 1),
        Point(1, -1), Point(1, 0), Point(1, 1)
    )

    val height = mapInfo.point.y
    val width = mapInfo.point.x
    private val mineCount = mapInfo.mineCount
    private var closedCount = mapInfo.point.getArea()

    init {
        mineMap
            .flatMap()
            .forEachIndexed { index, spot ->
                val point = Point(index / width, index % width)
                spot.setNearMineCount(countNearMine(point))
            }
    }

    fun get(point: Point): Spot = mineMap.get(point)

    fun open(point: Point): OpenStatus {
        val spot = get(point)
        if (spot.isOpen()) {
            return spot.getOpenStatus()
        }
        val openResult = spot.open()
        closedCount -= 1
        if (openResult == OpenStatus.ZERO) {
            validPoint(point)
                .filter { get(it).isOpen().not() }
                .forEach { open(it) }
        }

        return openResult
    }

    fun open(y: Int, x: Int): OpenStatus = open(Point(y, x))

    fun isOpened(y: Int, x: Int): Boolean = get(Point(y, x)).isOpen()

    fun isAllOpened(): Boolean = closedCount == mineCount

    fun viewSpot(point: Point): String = get(point).viewSpot()

    private fun countNearMine(point: Point): Int =
        validPoint(point).count { get(it).hasMine }

    private fun validPoint(point: Point): List<Point> =
        delta.map { point + it }
            .filter { it.y in 0 until height }
            .filter { it.x in 0 until width }
}
