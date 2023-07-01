package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition

@JvmInline
value class BoardRow(private val points: List<BoardPoint>) {
    fun size() = points.size

    fun plantMine(position: MinePosition): List<BoardPoint> {
        require(position.x < size()) { "지뢰 x 위치는 ${size()} 보다 작아야 합니다." }
        points[position.x].plantMine()
        return points
    }

    fun descs(): List<String> = points.map { it.desc() }

    companion object {
        const val BASE_X: Int = 0
        fun covered(width: Int) =
            BoardRow((BASE_X until width).map { BoardPoint.covered() })
    }
}
