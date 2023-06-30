package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.point.Point

data class MineBoard(private val board: Board, private val minePoints: MinePoints) {

    init {
        require(minePoints.size <= board.area()) { "지뢰 개수는 ${board.area()}개보다 많을 수 없습니다." }
        requireMaxHeight()
        requireMinHeight()
        requireMaxWidth()
        requireMinWidth()
    }

    private fun requireMaxHeight() =
        minePoints.forEach { require(it.y < height()) { "지뢰 y 위치는 ${height()} 보다 작아야 합니다." } }

    private fun requireMinHeight() =
        minePoints.forEach { require(it.y >= zero().y) { "지뢰 y 위치는 ${zero().y} 보다 작을 수 없습니다." } }
    
    private fun requireMaxWidth() = minePoints.forEach { require(it.x < width()) { "지뢰 x 위치는 ${width()} 보다 작아야 합니다." } }

    private fun requireMinWidth() =
        minePoints.forEach { require(it.x >= zero().x) { "지뢰 x 위치는 ${zero().x} 보다 작을 수 없습니다." } }

    fun isMineAt(point: Point) = minePoints.contains(point)

    fun width() = board.width()

    fun height() = board.height()

    fun zero(): Point = START

    companion object {
        private val START = Point.of(0, 0)
        fun of(board: Board, minePoints: MinePoints): MineBoard = MineBoard(board, minePoints)
    }
}