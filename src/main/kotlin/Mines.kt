class Mines(private val mineList: List<Mine>) {
    fun countMineAround(point: Point): Int = mineList.count { it.point.isAround(point) }
}
