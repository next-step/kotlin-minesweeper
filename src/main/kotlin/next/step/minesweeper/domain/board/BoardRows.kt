package next.step.minesweeper.domain.board

@JvmInline
value class BoardRows(private val rows: List<BoardRow>) {

    fun plantMine(position: BoardPosition) = row(position.y).plantMine(position.x)

    private fun row(y: Int) = rows[y]

    fun notifyMine(positions: BoardPositions) = positions.forEach { row(it.y).pointAt(it.x).notifyMine() }

    fun canUncover(): Boolean = rows.any { it.canUncover() }

    fun uncover(position: BoardPosition, area: BoardArea): Boolean {
        val point = row(position.y).uncover(position.x)
        if (row(position.y).pointAt(position.x).isMineFree()) {
            uncover(area.near(position).toMutableList(), mutableSetOf(position), area)
        }
        return point.isMine()
    }

    private tailrec fun uncover(
        queue: MutableList<BoardPosition>,
        visited: MutableSet<BoardPosition>,
        area: BoardArea,
    ) {
        if (queue.isEmpty()) return
        val position = queue.removeAt(0)
        visited.add(position)
        val point = row(position.y).pointAt(position.x)
        point.uncoverIfPossible()
        if (point.isMineFree()) {
            val nearPoints = area.near(position).filterNot { it in visited }
            queue.addAll(nearPoints)
            visited.addAll(nearPoints)
        }
        uncover(queue, visited, area)
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }
}
