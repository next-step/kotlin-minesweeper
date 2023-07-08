package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

@JvmInline
value class BoardPoints(private val points: Map<Position, BoardPoint>) : Map<Position, BoardPoint> by points {

    fun canUncover(): Boolean = points.values.any { it.canUncover() }

    fun uncover(position: Position, area: BoardArea): Boolean {
        val point = points[position]!!
        point.uncover()
        if (point.isMineFree()) {
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
        val point = points[position]!!
        point.uncoverIfPossible()
        if (point.isMineFree()) {
            val nears = area.near(position).filterNot { it in visited }
            queue.addAll(nears)
            visited.addAll(nears)
        }
        uncover(queue, visited, area)
    }

    fun groupByRow(): List<List<BoardPoint>> = points.keys.groupBy { it.y }
        .toSortedMap()
        .map { it.value.sortedBy { position -> position.x }.map { pos -> points[pos]!! } }

    companion object {
        fun of(area: BoardArea, mines: Positions): BoardPoints {
            val points = area.positions().associateWith { boardPoint(it, mines) }
            mines.forEach { mine -> area.near(mine).forEach { points[it]!!.notifyMine() } }
            return BoardPoints(points)
        }

        private fun boardPoint(
            position: Position,
            mines: Positions,
        ): BoardPoint {
            if (position in mines) {
                return BoardPoint.mine()
            }
            return BoardPoint.mineFree()
        }
    }
}
