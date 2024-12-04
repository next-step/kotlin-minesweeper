package minesweeper.domain

class Board(
    private val rows: Int,
    private val columns: Int,
    private val mineCount: Int,
) {

    val board: Array<Array<Boolean>>

    init {
        require(rows > 0) { "행은 1 이상이어야 합니다." }
        require(columns > 0) { "열은 1 이상이어야 합니다." }
        require(mineCount > 0) { "지뢰 개수는 1 이상이어야 합니다." }
        board = Array(rows) { Array(columns) { false } }
        placeMine()
    }

    private fun placeMine() {
        repeat(mineCount) {
            var row: Int
            var col: Int
            do {
                row = (0 until rows).random()
                col = (0 until columns).random()
            } while (this.board[row][col])
            this.board[row][col] = true
        }
    }
}
