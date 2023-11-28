package business

class Mines(mineList: List<Mine>) {
    private val mineList: List<Mine> = mineList.toList()
    fun countMineAround(point: Point): Int = mineList.count { it.point.isAround(point) }
    fun contains(point: Point): Boolean = mineList.any { it.point == point }
    fun size(): Int = mineList.size
}
