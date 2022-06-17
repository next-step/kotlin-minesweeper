package minesweeper.domain

class GameBoardSize(val x: Int, val y: Int) {
    init {
        require(x > 0 && y > 0) { "넓이와 높이는 모두 0보다 커야합니다." }
    }

    fun createPositions(): List<Position> {
        val positions = ArrayList<Position>()
        repeat(x) {
            positions.addAll(getXPositions(it))
        }
        return positions.toList()
    }

    private fun getXPositions(x: Int): ArrayList<Position> {
        val positions = ArrayList<Position>()
        repeat(y) {
            positions.add(Position(x, it))
        }
        return positions
    }
}
