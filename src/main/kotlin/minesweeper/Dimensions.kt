package minesweeper

data class Dimensions(val width: Int, val height: Int) {
    fun allPositions(): List<Position> {
        return (0 until height).flatMap { y ->
            (0 until width).map { x -> Position(x, y) }
        }
    }
}
