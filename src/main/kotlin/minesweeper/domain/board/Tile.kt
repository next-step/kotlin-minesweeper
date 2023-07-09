package minesweeper.domain.board

class Tile(private val cell: Cell, private val neighborCells: List<Cell>) {
    fun isMineActive() = cell.isMineActive()

    fun getNeighborMineCount() = neighborCells.count { it.isMineActive() }
}
