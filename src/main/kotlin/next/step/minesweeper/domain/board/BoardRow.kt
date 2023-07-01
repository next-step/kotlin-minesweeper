package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition

@JvmInline
value class BoardRow(private val points: List<BoardPoint>) {

    fun plantMine(position: MinePosition) {
        require(position.x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        points[position.x].plantMine()
    }

    fun size() = points.size

    fun pointAt(x: Int): BoardPoint = points[x]

    fun points() = points.toList()

    companion object {
        fun covered(width: BoardWidth) = BoardRow(width.range().map { BoardPoint.covered() })

        fun mineFree(width: BoardWidth) = BoardRow(width.range().map { BoardPoint.mineFree() })
    }
}
