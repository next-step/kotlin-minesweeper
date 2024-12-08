package minesweeper.domain

class Board(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
) {

    val board: List<Row>

    init {
        validateInput()
        board = List(height) { Row(width) }
        placeMine()
        countAdjacentMines()
    }

    private fun validateInput() {
        require(height > 0) { "행은 1 이상이어야 합니다." }
        require(width > 0) { "열은 1 이상이어야 합니다." }
        require(mineCount > 0) { "지뢰 개수는 1 이상이어야 합니다." }
        require(height * width > mineCount) { "지뢰 개수는 전체 칸의 개수보다 작아야 합니다." }
    }


    private fun placeMine() {
        val minePlaces = generateMinePlaces()
        for (minePlace in minePlaces) {
            val row = minePlace / width
            val col = minePlace % width
            board[row].setMine(col)
        }
    }

    private fun countAdjacentMines() {
        for (row in 0 until height) {
            for (col in 0 until width) {
                updateAdjacentMineCountOfCell(row, col)
            }
        }
    }

    private fun updateAdjacentMineCountOfCell(row: Int, col: Int) {
        val currentCell = board[row].cells[col]
        if (currentCell is Land) {
            val adjacentMines = getAdjacentCells(row, col)
            currentCell.updateAdjacentMines(adjacentMines)
        }
    }

    private fun getAdjacentCells(row: Int, col: Int): List<Cell> {
        val adjacentCells = mutableListOf<Cell>()
        for (i in -1..1) {
            for (j in -1..1) {
                val newRow = row + i
                val newCol = col + j
                if (outOfBound(newRow, newCol)) {
                    continue
                }
                adjacentCells.add(board[newRow].cells[newCol])
            }
        }
        return adjacentCells
    }

    private fun outOfBound(newRow: Int, newCol: Int) = newRow < 0 || newRow >= height || newCol < 0 || newCol >= width

    private fun generateMinePlaces(): List<Int> {
        return (0 until height * width).shuffled()
            .take(mineCount)
    }

    fun countMines(): Int {
        return board.sumOf { row -> row.countMines() }
    }
}
