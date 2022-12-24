package minesweeper.domain

class Board(val matrix: Matrix, mineCount: Int) {
    init {
        createRandomMine(mineCount)
        createSafe()
    }

    private fun createRandomMine(count: Int) {
        require(count <= matrix.width() * matrix.height()) {
            "지뢰 개수는 지도에 존재하는 모든 필드의 수보다 클 수 없습니다."
        }

        coordinates().shuffled()
            .take(count)
            .forEach { matrix.set(it, Mine()) }
    }

    private fun createSafe() {
        coordinates()
            .forEach { if (matrix.get(it) !is Mine) matrix.set(it, Safe(aroundMineCount(it))) }
    }

    private fun aroundMineCount(coordinate: Coordinate): Int {
        return CoordinateDirection.around(coordinate)
            .filter { it.x in 0 until matrix.width() && it.y in 0 until matrix.height() }
            .count { matrix.get(it) is Mine }
    }

    private fun coordinates() = matrix.rows.indices.flatMap { y: Int -> rowsCoordinates(y) }
    private fun rowsCoordinates(y: Int) =
        (0 until matrix.rows[y].fields.size).map { x: Int -> Coordinate(x, y) }
}
