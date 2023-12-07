package minesweeper.domain

class CountingBoard(private val board: Board) {
    private val countsOfAroundMines: MutableMap<Coordinate, Int> = mutableMapOf()

    init {
        countAllAroundMine()
    }

    fun countAroundMine(row: Int, col: Int): Int {
        return countsOfAroundMines[Coordinate(row, col)] ?: throw IllegalArgumentException("존재하지 않는 좌표입니다.")
    }

    private fun countAllAroundMine() {
        (0 until board.metadata.width).flatMap { row ->
            (0 until board.metadata.height).map { col ->
                val currentCell = board.at(row, col)
                if (currentCell is EmptyCell) {
                    val currentCoordinate = Coordinate(row, col)
                    countsOfAroundMines[currentCoordinate] = countAroundMine(currentCoordinate)
                }
            }
        }
    }

    private fun countAroundMine(coordinate: Coordinate): Int {
        var mineCount = 0
        val maxHeight = board.metadata.height
        val maxWidth = board.metadata.width

        for (aroundCoordinate in Board.AROUND_COORDINATES) {
            val nextCoordinate = coordinate + aroundCoordinate
            if (nextCoordinate.isOutOfBound(Board.MIN_HEIGHT, maxHeight, Board.MIN_WIDTH, maxWidth)) {
                continue
            }
            if (board.at(nextCoordinate.row, nextCoordinate.col) is MineCell) {
                mineCount++
            }
        }
        return mineCount
    }
}
