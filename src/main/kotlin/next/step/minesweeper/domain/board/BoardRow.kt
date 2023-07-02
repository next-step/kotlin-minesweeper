package next.step.minesweeper.domain.board

@JvmInline
value class BoardRow(private val points: List<BoardPoint>) {

    fun plantMine(x: Int) {
        require(x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        points[x].plantMine()
    }

    fun size() = points.size

    fun pointAt(x: Int): BoardPoint = points[x]

    fun points() = points.toList()

    companion object {
        fun covered(width: BoardWidth) = BoardRow(width.range().map { BoardPoint.covered() })

        fun mineFree(width: BoardWidth) = BoardRow(width.range().map { BoardPoint.mineFree() })
    }
}
