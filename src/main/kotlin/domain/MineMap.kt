package domain

class MineMap(
    val point: Point,
    mineCount: Int = 10,
    private val mineMap: List<List<Spot>> = RandomMineMap.newMineMap(point, mineCount)
) {

    private val delta = listOf(
        Point(-1, -1), Point(-1, 0), Point(-1, 1),
        Point(0, -1), Point(0, 1),
        Point(1, -1), Point(1, 0), Point(1, 1)
    )

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

    fun open(point: Point): OpenStatus = get(point).open()

    fun open(y: Int, x: Int): OpenStatus = get(y, x).open()

    private fun countNearMine(point: Point): Int =
        delta.map { point + it }
            .filter { it.y in 0 until this.point.y }
            .filter { it.x in 0 until this.point.x }
            .count { get(it).hasMine }

    private fun countNearMine(y: Int, x: Int): Int = countNearMine(Point(y, x))
}
