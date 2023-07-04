package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position

data class BoardArea(private val height: BoardHeight, private val width: BoardWidth) {

    fun requireContains(x: Int, y: Int) {
        width.requireInRange(x)
        height.requireInRange(y)
    }

    fun contains(x: Int, y: Int): Boolean = height.inRange(y) && width.inRange(x)

    fun checkMaxCount(count: Int) = require(count <= area()) { "${area()}개보다 더 많을 수 없습니다." }

    private fun area(): Int = width.width() * height.height()

    fun <R, P> rangeMap(row: (List<P>) -> R, point: (Int, Int) -> P): List<R> =
        height.rangeMap { y -> row(width.rangeMap { point(it, y) }) }

    fun select(selector: () -> Position): BoardPosition {
        val position = selector()
        requireContains(position.x, position.y)
        return BoardPosition(position, this)
    }

    companion object {
        fun of(height: Int, width: Int): BoardArea = BoardArea(BoardHeight(height), BoardWidth(width))
    }
}
