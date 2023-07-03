package next.step.minesweeper.domain.board

@JvmInline
value class BoardRows(private val rows: List<BoardRow>) {

    fun plantMine(position: BoardPosition) = row(position.y()).plantMine(position.x())

    private fun row(y: Int) = rows[y]

    fun notifyMine(position: BoardPosition) = position.near().forEach { row(it.y()).pointAt(it.x()).notifyMine() }

    fun canUncover(): Boolean = rows.any { it.canUncover() }

    fun uncover(position: BoardPosition): Boolean {
        val row = row(position.y())
        val point = row.uncover(position.x())
        uncoverNear(point, position, mutableSetOf(position))
        return point.isMine()
    }

    private fun uncoverNear(point: BoardPoint, position: BoardPosition, visited: MutableSet<BoardPosition>) {
        if (!point.isMineFree()) return
        position.near().filterNot { visited.contains(it) }.forEach {
            val near = row(it.y()).pointAt(it.x())
            if (near.canUncover()) {
                near.uncover()
                visited.add(it)
                uncoverNear(near, it, visited)
            }
        }
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }
}
