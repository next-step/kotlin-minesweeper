package next.step.minesweeper.domain.board

@JvmInline
value class BoardRow(private val points: List<BoardPoint>) {

    fun plantMine(x: Int) = pointAt(x).plantMine()

    fun pointAt(x: Int): BoardPoint {
        require(x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        return points[x]
    }

    private fun size(): Int = points.size

    fun uncover(x: Int): BoardPoint {
        val point = pointAt(x)
        point.uncover()
        return point
    }

    fun canUncover(): Boolean = points.any { it.canUncover() }

    fun points() = points.toList()

    fun notifyMine(x: Int) = pointAt(x).notifyMine()

    fun isMineFree(x: Int) = pointAt(x).isMineFree()
}
