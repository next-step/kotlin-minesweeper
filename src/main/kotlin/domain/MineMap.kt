package domain

class MineMap(
    val point: Point,
    private val mineCount: Int,
    private val mineMap: List<List<Spot>> = RandomMineMap.newMineMap(point, mineCount)
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
                get(y, x).setNearMineCount(countNearMine(y, x))
            }
        }
    }

    fun get(y: Int, x: Int): Spot = mineMap[y][x]

    fun get(point: Point): Spot = get(point.y, point.x)

    fun open(point: Point): OpenStatus {
        val openResult = get(point).open()
        closedCount -= 1
        if (openResult == OpenStatus.ZERO) {
            validPoint(point)
                .filter { get(it).isOpen().not() }
                .forEach { open(it) }
        }

        return openResult
    }

    fun open(y: Int, x: Int): OpenStatus = open(Point(y, x))

    fun isOpened(y: Int, x: Int): Boolean = get(y, x).isOpen()

    fun isOpened(point: Point): Boolean = get(point).isOpen()

    fun isAllOpened(): Boolean = closedCount == mineCount

    private fun countNearMine(point: Point): Int =
        validPoint(point).count { get(it).hasMine }

    private fun countNearMine(y: Int, x: Int): Int = countNearMine(Point(y, x))

    private fun validPoint(point: Point): List<Point> =
        delta.map { point + it }
            .filter { it.y in 0 until this.point.y }
            .filter { it.x in 0 until this.point.x }
}
