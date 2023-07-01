package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition

@JvmInline
value class BoardRow(val points: List<BoardPoint>) {
    fun size() = points.size

    fun plantMine(position: MinePosition) {
        require(position.x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        points[position.x].plantMine()
    }

    companion object {
        fun covered(width: BoardWidth) = BoardRow(width.range().map { BoardPoint.covered() })
    }
}
