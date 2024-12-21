package minesweeper

data class Dimensions(val width: Int, val height: Int) {
    val totalCells: Int
        get() = width * height

    init {
        require(width > 0) { "폭은 0보다 커야 합니다." }
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    fun allPositions(): List<Position> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x -> Position(x, y) }
        }
    }
}
