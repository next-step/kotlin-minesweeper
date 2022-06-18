package minesweeper.domain

class GameBoardSize(val width: Int, val height: Int) {
    init {
        require(width > 0 && height > 0) { "넓이와 높이는 모두 0보다 커야합니다." }
    }

    fun createPositions(): List<Position> {
        val positions = ArrayList<Position>()
        repeat(width) {
            positions.addAll(getXPositions(it))
        }
        return positions.toList()
    }

    private fun getXPositions(x: Int): ArrayList<Position> {
        val positions = ArrayList<Position>()
        repeat(height) {
            positions.add(Position(x, it))
        }
        return positions
    }
}
