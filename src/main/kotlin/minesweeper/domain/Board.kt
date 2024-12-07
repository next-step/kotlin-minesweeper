package minesweeper.domain

class Board(
    private val rows: Int,
    private val columns: Int,
    private val mineCount: Int,
) {

    val board: List<Row>

    init {
        validateInput()
        board = List(rows) { Row(columns) }
        placeMine()
    }

    private fun validateInput() {
        require(rows > 0) { "행은 1 이상이어야 합니다." }
        require(columns > 0) { "열은 1 이상이어야 합니다." }
        require(mineCount > 0) { "지뢰 개수는 1 이상이어야 합니다." }
        require(rows * columns > mineCount) { "지뢰 개수는 전체 칸의 개수보다 작아야 합니다." }
    }

    private fun placeMine() {
        val minePlaces = generateMinePlaces()
        for (minePlace in minePlaces) {
            val row = minePlace / columns
            val col = minePlace % columns
            board[row].setMine(col)
        }
    }

    private fun generateMinePlaces(): List<Int> {
        return (0 until rows * columns).shuffled()
            .take(mineCount)
    }

    fun countMines(): Int {
        return board.sumOf { row -> row.countMines() }
    }
}
