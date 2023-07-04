package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

@JvmInline
value class BoardRows(private val rows: List<BoardRow>) {

    fun plantMine(position: Position) = row(position.y).plantMine(position.x)

    private fun row(y: Int) = rows[y]

    fun notifyMine(positions: Positions) = positions.forEach { row(it.y).notifyMine(it.x) }

    fun canUncover(): Boolean = rows.any { it.canUncover() }

    fun uncover(position: Position, area: BoardArea): Boolean {
        val point = row(position.y).uncover(position.x)
        if (row(position.y).isMineFree(position.x)) {
            uncover(area.near(position).toMutableList(), mutableSetOf(position), area)
        }
        return point.isMine()
    }

    private tailrec fun uncover(
        queue: MutableList<Position>,
        visited: MutableSet<Position>,
        area: BoardArea,
    ) {
        if (queue.isEmpty()) return
        val position = queue.removeAt(0)
        visited.add(position)
        val point = row(position.y).pointAt(position.x)
        point.uncoverIfPossible()
        if (point.isMineFree()) {
            val nears = area.near(position).filterNot { it in visited }
            queue.addAll(nears)
            visited.addAll(nears)
        }
        uncover(queue, visited, area)
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }
}
