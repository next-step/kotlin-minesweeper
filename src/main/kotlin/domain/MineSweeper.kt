package domain

class MineSweeper(private val board: Board, private val selector: PositionSelector) {
    fun sweepMine(position: Position = Position()): Boolean {
        val cell = getCell(position)
        if (!cell.isOpen()) {
            open(position, setOf())
        }
        return !cell.isMine()
    }

    private fun open(position: Position, visited: Set<Position>) {
        val positions = selector
            .adjacentPositions(position)
            .filterNot { getCell(it).isOpen() || visited.contains(it) }
        positions
            .forEach { getCell(it).open() }
        positions
            .filter { getCell(it).isBlank() }
            .map { it to visited + setOf(it) }
            .forEach { (position, visited) -> open(position, visited) }
    }

    private fun getCell(position: Position): Cell = board.getCell(position)
}
