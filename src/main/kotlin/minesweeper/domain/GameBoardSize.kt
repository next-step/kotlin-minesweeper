package minesweeper.domain

class GameBoardSize(private val width: Int, private val height: Int) {
    init {
        require(width > 0 && height > 0) { "넓이와 높이는 모두 0보다 커야합니다." }
    }

    fun createPositions(): List<Position> {
        return (0..width).flatMap { getWidthPositions(it) }
    }

    private fun getWidthPositions(x: Int): List<Position> {
        return (0..height).map { Position(x, it) }
    }
}
