package minesweeper.domain

class Board(
    private val config: BoardConfig,
) {

    private val _board: MutableList<MutableList<Cell>> = MutableList(config.height) {
        MutableList(config.width) { Land() }
    }

    val board get() = _board.map { it.toList() }

    private val boardOpener = BoardOpener(this)

    init {
        placeMine()
        countAdjacentMineCountOfBoard()
    }

    private fun placeMine() {
        val minePlaces = config.minePlacementStrategy.placeMines(
            config.height,
            config.width,
            config.mineCount,
        )
        minePlaces.forEach {
            val (row, col) = placeToCoordinates(it)
            _board[row][col] = Mine()
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
        val currentCell = _board[row][col]
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
            if (outOfBound(newRow, newCol)) {
                continue
            }
            adjacentCells.add(_board[newRow][newCol])
        }

        return adjacentCells
    }

    fun outOfBound(row: Int, col: Int): Boolean {
        return row !in 0 until config.height || col !in 0 until config.width
    }

    fun countMines(): Int {
        return _board.sumOf { line -> line.count { it is Mine } }
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
        for (row in 0 until config.height) {
            for (col in 0 until config.width) {
                _board[row][col].open()
            }
        }
    }

    private fun isGameClear(): Boolean {
        return _board.sumOf { line -> line.count { it.isOpened } } == config.getLandCount()
    }
}
