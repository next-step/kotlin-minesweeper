package domain

class MineMap(
    val point: Point,
    private val mineCount: Int,
    private val mineMap: ArrayMap = RandomMineMap.newRandomMineMap(point, mineCount)
) {

    private val delta = listOf(
        Point(-1, -1), Point(-1, 0), Point(-1, 1),
        Point(0, -1), Point(0, 1),
        Point(1, -1), Point(1, 0), Point(1, 1)
    )

    private var closedCount = point.getArea()

    init {
        require(point.y > 0)
        require(point.x > 0)
        require(mineCount in 0 until point.getArea())

        repeat(point.y) { y ->
            repeat(point.x) { x ->
                val point = Point(y, x)
                get(point).setNearMineCount(countNearMine(point))
            }
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
            .filter { it.y in 0 until this.point.y }
            .filter { it.x in 0 until this.point.x }
}
