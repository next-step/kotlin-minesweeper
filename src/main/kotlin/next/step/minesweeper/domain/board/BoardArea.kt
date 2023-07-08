package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

data class BoardArea(private val height: BoardHeight, private val width: BoardWidth) {

    fun requireContains(x: Int, y: Int) {
        width.requireInRange(x)
        height.requireInRange(y)
    }

    operator fun contains(position: Position): Boolean =
        height.inRange(position.y) && width.inRange(position.x)

    fun checkMaxCount(count: Int) = require(count <= area()) { "${area()}개보다 더 많을 수 없습니다." }

    private fun area(): Int = width.width() * height.height()

    fun <R, P> rangeMap(row: (List<P>) -> R, point: (Int, Int) -> P): List<R> =
        height.rangeMap { y -> row(width.rangeMap { point(it, y) }) }

    fun positions(): Positions =
        Positions(height.rangeMap { y -> width.rangeMap { x -> Position(x, y) } }.flatten().toSet())

    fun select(selector: () -> Position): Position {
        val position = selector()
        requireContains(position.x, position.y)
        return position
    }

    fun near(position: Position): Positions = Positions(position.near().filter { it in this }.toSet())

    companion object {
        fun of(height: Int, width: Int): BoardArea = BoardArea(BoardHeight(height), BoardWidth(width))
    }
}
