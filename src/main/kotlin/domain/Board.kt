package domain

class Board(private val mineManager: MineManager) {
    private lateinit var cells: List<Cell>
    private var height: Int = 0
    private var width: Int = 0

    fun initializeBoard(height: Int, width: Int, minePositions: List<Position>) {
        this.height = height
        this.width = width
        cells = List(height * width) { index ->
            Cell(Position(index % width, index / width))
        }

        mineManager.minePlacer.placeMines(this, minePositions)
        cells.forEach { cell ->
            cell.adjacentMines = calculateAdjacentMines(cell.position)
        }
    }

    private fun calculateAdjacentMines(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(this, position)
    }

    fun placeMineAt(position: Position) {
        findCell(position).placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).isMine()

    fun countMines(): Int = cells.count { it.isMine() }

    private fun findCell(position: Position): Cell = cells.first { it.position == position }
}
