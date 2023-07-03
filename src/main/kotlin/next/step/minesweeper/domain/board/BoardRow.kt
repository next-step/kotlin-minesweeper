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

    fun uncoverUntilPossible(x: Int) {
        uncoverUntilLeft(x)
        uncoverUntilRight(x)
    }

    private fun uncoverUntilLeft(x: Int) {
        uncoverUntilPossible((x - 1) downTo 0)
    }

    private fun uncoverUntilRight(x: Int) {
        uncoverUntilPossible(x + 1 until size())
    }

    private fun uncoverUntilPossible(xProgression: IntProgression) {
        xProgression.forEach {
            if (!pointAt(it).canUncover()) return
            pointAt(it).uncover()
        }
    }

    fun canUncover(x: Int): Boolean = pointAt(x).canUncover()

    fun canUncover(): Boolean = points.any { it.canUncover() }

    fun points() = points.toList()
}
