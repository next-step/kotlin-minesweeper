package domain

class GameBoard(val height: Int, val width: Int) {
    private val board = Board(height, width)

    fun placeMines(mineCount: Int) {
        val allPositions = (0 until height).flatMap { y -> (0 until width).map { x -> Position(x, y) } }
        allPositions.shuffled().take(mineCount).forEach { board.placeMineAt(it) }
    }

    fun countMines(): Int = board.countMines()
}
