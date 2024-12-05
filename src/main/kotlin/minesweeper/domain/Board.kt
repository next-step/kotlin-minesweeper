package minesweeper.domain

class Board(
    private val rows: Int,
    private val columns: Int,
    private val mineCount: Int,
) {

    private val board: List<Row>

    init {
        require(rows > 0) { "행은 1 이상이어야 합니다." }
        require(columns > 0) { "열은 1 이상이어야 합니다." }
        require(mineCount > 0) { "지뢰 개수는 1 이상이어야 합니다." }
        require(rows * columns > mineCount) { "지뢰 개수는 전체 칸의 개수보다 작아야 합니다." }
        board = List(rows) { Row(columns) }
        placeMine()
    }

    fun display(): String {
        return board.joinToString("\n") { it.display() }
    }

    fun getCell(row: Int, col: Int): Cell {
        return board[row].getCell(col)
    }

    fun countMines(): Int {
        return board.sumBy { row -> row.countMines() }
    }

    private fun placeMine() {
        repeat(mineCount) {
            var row: Int
            var col: Int
            do {
                row = (0 until rows).random()
                col = (0 until columns).random()
            } while (getCell(row, col).isMine)
            getCell(row, col).isMine = true
        }
    }
}
