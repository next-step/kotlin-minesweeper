package minesweeper

data class Dimensions(val width: Int, val height: Int, val mineCount: Int) {
    init {
        require(width > 0) { "폭은 0보다 커야 합니다." }
        require(height > 0) { "높이는 0보다 커야 합니다." }
        require(mineCount > 0) { "지뢰 갯수는 0보다 커야 합니다." }
        require(mineCount < width * height) { "지뢰 갯수는 최대치 보다 작아야 합니다" }
    }

    fun allPositions(): List<Position> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x -> Position(x, y) }
        }
    }
}
