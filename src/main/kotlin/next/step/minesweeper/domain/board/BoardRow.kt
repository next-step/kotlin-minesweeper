package next.step.minesweeper.domain.board

@JvmInline
value class BoardRow(private val points: List<BoardPoint>) {

    fun pointAt(x: Int): BoardPoint = points[x]

    fun points() = points.toList()

    fun canUncover(): Boolean = points.any { it.canUncover() }

    fun plantMine(x: Int) {
        require(x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        points[x].plantMine()
    }

    private fun size() = points.size
}
