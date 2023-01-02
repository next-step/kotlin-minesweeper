package minesweeper.domain

class Board(val matrix: Matrix, mineCount: Int) {
    private val mineCoordinates: List<Coordinate>
    private val safeCoordinates: List<Coordinate>

    init {
        require(mineCount <= matrix.width * matrix.height) {
            "지뢰 개수는 지도에 존재하는 모든 필드의 수보다 클 수 없습니다."
        }

        mineCoordinates = getMineCoordinates(mineCount)
        safeCoordinates = getSafeCoordinates()
        makeMap()
    }
    fun open(coordinate: Coordinate) {
        matrix.open(coordinate)
    }

    fun isWin() = safeCoordinates.all { matrix.rows[it.rows][it.cols].isOpened() }
    fun isLose() = mineCoordinates.any { matrix.rows[it.rows][it.cols].isOpened() }

    private fun makeMap() {
        mineCoordinates.forEach { matrix.landMine(it) }
        safeCoordinates.forEach { matrix.rows[it.rows][it.cols].landSafe(matrix.aroundMineCount(it)) }
    }

    private fun getSafeCoordinates() = matrix.coordinates
        .filter { it !in mineCoordinates }

    private fun getMineCoordinates(mineCount: Int) = matrix.coordinates
        .shuffled()
        .take(mineCount)

    companion object {
        fun of(width: Int, height: Int, mineCount: Int) =
            Board(Matrix.of(height, width), mineCount)
    }
}
