package domain

class MineSweeper(
    private val board: Board,
    private val selector: PositionSelector
) {
    fun sweepMine(position: Position = Position()): Boolean {
        val cell = getCell(position)
        if (!cell.isOpen()) {
            open(position, emptySet())
        }
        return !cell.hasMine()
    }

    private fun open(position: Position, visited: Set<Position>) {
        selector
            .adjacentPositions(position)
            .filterNot { getCell(it).isOpen() || visited.contains(it) }
            .map {
                getCell(it).open()
                it
            }.filter {
                getCell(it).isBlank()
            }.forEach {
                open(
                    position = it,
                    visited = visited + setOf(it)
                )
            }
    }

    private fun getCell(position: Position): Cell = board.getCell(position)
}
