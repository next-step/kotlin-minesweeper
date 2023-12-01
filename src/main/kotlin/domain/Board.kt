package domain

class Board(
    private val mineManager: MineManager
) {
    private lateinit var cells: List<Cell>

    fun initializeBoard(minePositions: List<Position>) {
        mineManager.minePlacer.placeMines(this, minePositions)
        cells.forEach { cell ->
            cell.adjacentMines = mineManager.mineCounter.countMinesAround(this, cell.position)
        }
    }

    fun placeMineAt(position: Position) {
        findCell(position).placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).isMine()

    fun countMines(): Int = cells.count { it.isMine() }

    private fun findCell(position: Position): Cell = cells.first { it.position == position }
}
