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
        if (point.isMineFree()) {
            row.uncoverUntilPossible(position.x())
            uncoverUntilTop(position)
            uncoverUntilBottom(position)
        }
        return point.isMine()
    }

    private fun uncoverUntilTop(position: BoardPosition) {
        uncoverUntilPossible((position.y() - 1) downTo 0, position.x())
    }

    private fun uncoverUntilBottom(position: BoardPosition) {
        uncoverUntilPossible(position.y() + 1 until rows.size, position.x())
    }

    private fun uncoverUntilPossible(yProgression: IntProgression, x: Int) {
        yProgression.forEach {
            if (!row(it).canUncover(x)) return
            row(it).uncover(x)
        }
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }
}
