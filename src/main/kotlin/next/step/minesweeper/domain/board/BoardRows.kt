package next.step.minesweeper.domain.board

@JvmInline
value class BoardRows(private val rows: List<BoardRow>) {

    fun plantMine(position: BoardPosition) = row(position.y()).plantMine(position.x())

    private fun row(y: Int) = rows[y]

    fun notifyMine(position: BoardPosition) = position.near().forEach { row(it.y()).pointAt(it.x()).notifyMine() }

    fun canUncover(): Boolean = rows.any { it.canUncover() }

    fun uncoverRecursive(position: BoardPosition): Boolean {
        val point = row(position.y()).uncover(position.x())
        uncoverNear(position, mutableSetOf(position))
        return point.isMine()
    }

    private fun uncoverNear(position: BoardPosition, visited: MutableSet<BoardPosition>) {
        if (!row(position.y()).pointAt(position.x()).isMineFree()) return
        position.near().filterNot { visited.contains(it) }.forEach {
            val near = row(it.y()).pointAt(it.x())
            if (near.canUncover()) {
                near.uncover()
                visited.add(it)
                uncoverNear(it, visited)
            }
        }
    }

    fun uncoverLoop(position: BoardPosition): Boolean {
        val point = row(position.y()).uncover(position.x())
        if (row(position.y()).pointAt(position.x()).isMineFree()) {
            uncoverLoop(position, mutableSetOf(position))
        }
        return point.isMine()
    }

    private fun uncoverLoop(position: BoardPosition, visited: MutableSet<BoardPosition>) {
        val queue = position.near().toMutableList()
        while (queue.isNotEmpty()) {
            val pos = queue.removeAt(0)
            visited.add(pos)
            val point = row(pos.y()).pointAt(pos.x())
            if (point.canUncover()) {
                point.uncover()
            }
            if (point.isMineFree()) {
                queue.addAll(pos.near().filterNot { it in visited })
            }
        }
    }

    fun uncoverTailRec(position: BoardPosition): Boolean {
        val point = row(position.y()).uncover(position.x())
        if (row(position.y()).pointAt(position.x()).isMineFree()) {
            val queue = position.near().toMutableList()
            uncoverTailRec(queue, mutableSetOf(position))
        }
        return point.isMine()
    }

    private tailrec fun uncoverTailRec(queue: MutableList<BoardPosition>, visited: MutableSet<BoardPosition>) {
        if (queue.isEmpty()) return
        val position = queue.removeAt(0)
        visited.add(position)
        val point = row(position.y()).pointAt(position.x())
        if (point.canUncover()) {
            point.uncover()
        }
        if (point.isMineFree()) {
            queue.addAll(position.near().filterNot { it in visited })
        }
        uncoverTailRec(queue, visited)
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }
}
