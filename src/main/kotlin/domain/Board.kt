package domain

class Board(private val row: Int, private val col: Int, private val mineCount: Int) {
    init {
        require(row > 0) { INVALID_ROW_SIZE }
        require(col > 0) { INVALID_COLUMN_SIZE }
        require(row * col >= mineCount) { INVALID_MINE_COUNT }
    }

    companion object {
        private const val INVALID_ROW_SIZE = "높이는 1이상입니다."
        private const val INVALID_COLUMN_SIZE = "너비는 1이상입니다."
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
