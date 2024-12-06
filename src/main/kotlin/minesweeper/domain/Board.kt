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

    private fun placeMine() {
        val minePlaces = generateMinePlaces()
        for (minePlace in minePlaces) {
            val row = minePlace / columns
            val col = minePlace % columns
            board[row].getCell(col).isMine = true
        }
    }

    private fun generateMinePlaces(): List<Int> {
        return (0..rows * columns).shuffled()
            .take(mineCount)
    }

    fun display(): String {
        return board.joinToString("\n") { it.display() }
    }

    fun countMines(): Int {
        return board.sumOf { row -> row.countMines() }
    }
}
