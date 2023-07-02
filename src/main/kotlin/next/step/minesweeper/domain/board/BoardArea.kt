package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.NearPositionDelta
import next.step.minesweeper.domain.position.Position

data class BoardArea(private val height: BoardHeight, private val width: BoardWidth) {

    fun requireContains(x: Int, y: Int) {
        width.requireInRange(x)
        height.requireInRange(y)
    }

    operator fun contains(position: Position): Boolean = height.inRange(position.y) && width.inRange(position.x)

    fun requireArea(count: Int) = require(count <= area()) { "${area()}개보다 더 넣을 수 없습니다." }

    private fun area(): Int = width.width() * height.height()

    fun <R, P> rangeMap(row: (List<P>) -> R, point: (Int, Int) -> P): List<R> =
        height.rangeMap { y -> row(width.rangeMap { point(it, y) }) }

    fun nearForEach(x: Int, y: Int, consume: (Position) -> Unit) =
        NearPositionDelta.nearInArea(x, y, this).forEach(consume)

    fun select(selector: () -> Position): Position {
        val position = selector()
        requireContains(position.x, position.y)
        return position
    }

    companion object {
        fun of(height: Int, width: Int): BoardArea = BoardArea(BoardHeight(height), BoardWidth(width))
    }
}
