package minesweeper

class BoardGenerator(private val width: Int, private val height: Int, private val mineCount: Int) {
    init {
        require(width > 0 && height > 0 && mineCount > 0) { "너비, 높이, 지뢰 갯수는 0보다 커야합니다" }
        require(width * height > mineCount) { "지뢰 갯수는 너비와 높이를 곱한 값보다 작아야 합니다" }
    }

    fun generateRandomCell(): List<Cell> {
        val cellSize = width * height
        val initCells = (1..cellSize - mineCount).map { Cell(false) } + (1..mineCount).map { Cell(true) }
        return initCells.shuffled()
    }
}
