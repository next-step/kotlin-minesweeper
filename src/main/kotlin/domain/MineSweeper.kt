package domain

class MineSweeper(private val board: Board, private val selector: PositionSelector) {
    fun sweepMine(position: Position = Position()): Boolean {
        if (!board.isOpen(position)) {
            open(position, mutableSetOf())
        }
        return !board.isMine(position)
    }

    private fun open(position: Position, visited: MutableSet<Position>) {
        val positions = selector.adjacentPositions(position)
            .filterNot { board.isOpen(it) || visited.contains(it) }
        positions.forEach { board.open(it) }
        positions.filter { board.isBlank(it) }.forEach {
            visited.add(it)
            open(it, visited)
        }
    }
}
