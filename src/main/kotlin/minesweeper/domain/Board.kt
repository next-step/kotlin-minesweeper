package minesweeper.domain

class Board(
    private val config: BoardConfig,
) {

    private val cells: Cells = Cells(
        height = config.height,
        width = config.width,
    )

    val board get() = cells.toBoard()

    private val boardOpener = BoardOpener(cells)

    init {
        placeMine()
        countAdjacentMineCountOfBoard()
    }

    private fun placeMine() {
        val minePlaces = config.placeMines()
        minePlaces.forEach {
            val (row, col) = placeToCoordinates(it)
            cells.setMine(row, col)
        }
    }

    private fun placeToCoordinates(place: Int): Pair<Int, Int> {
        val row = place / config.width
        val col = place % config.width
        return row to col
    }

    private fun countAdjacentMineCountOfBoard() {
        for (row in 0 until config.height) {
            updateAdjacentMineCountOfLine(row)
        }
    }

    private fun updateAdjacentMineCountOfLine(row: Int) {
        for (col in 0 until config.width) {
            updateAdjacentMineCountOfCell(row, col)
        }
    }

    private fun updateAdjacentMineCountOfCell(row: Int, col: Int) {
        val currentCell = cells.getCell(row, col)
        if (currentCell is Land) {
            val adjacentMines = getAdjacentCells(row, col)
            currentCell.updateAdjacentMines(adjacentMines)
        }
    }

    private fun getAdjacentCells(row: Int, col: Int): List<Cell> {
        val adjacentCells = mutableListOf<Cell>()

        for (direction in Direction.entries) {
            val newRow = row + direction.dx
            val newCol = col + direction.dy
            if (cells.isOutOfBound(newRow, newCol)) {
                continue
            }
            adjacentCells.add(cells.getCell(newRow, newCol))
        }

        return adjacentCells
    }

    fun countMines(): Int {
        return cells.getMineCount()
    }

    fun open(row: Int, col: Int): OpenResult {
        val result = boardOpener.open(row, col)

        if (isGameClear()) {
            openAllCell()
            return OpenResult.WIN
        }

        return result
    }

    private fun openAllCell() {
        cells.openAllCellsIncludeMines()
    }

    private fun isGameClear(): Boolean {
        return cells.isAllLandOpened()
    }
}
