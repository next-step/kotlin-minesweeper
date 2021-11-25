package domain

class MineSweeper(private val board: Board, private val selector: PositionSelector) {
    fun sweepMine(position: Position = Position()): Boolean {
        val cell = board.getCell(position)
        if (!cell.isOpen()) {
            open(position, mutableSetOf())
        }
        return !cell.isMine()
    }

    private fun open(position: Position, visited: MutableSet<Position>) {
        val positions = selector.adjacentPositions(position)
            .filterNot { board.getCell(it).isOpen() || visited.contains(it) }
        positions.forEach { board.getCell(it).open() }
        positions.filter { board.getCell(it).isBlank() }.forEach {
            visited.add(it)
            open(it, visited)
        }
    }
}
