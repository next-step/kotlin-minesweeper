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

        matrix.coordinates().shuffled()
            .take(count)
            .forEach { matrix[it] = Mine() }
    }

    private fun createSafe() {
        matrix.coordinates()
            .forEach { if (matrix[it] !is Mine) matrix[it] = Safe(aroundMineCount(it)) }
    }

    private fun aroundMineCount(coordinate: Coordinate): Int {
        return CoordinateDirection.around(coordinate)
            .filter { it.x in 1..matrix.width() && it.y in 1..matrix.height() }
            .count { matrix[it] is Mine }
    }
}
