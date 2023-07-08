package minesweeper.domain

class Board(
    private val rows: List<Row>,
) : List<Row> by rows {
    private val mineCount: Int = Coordinates.all(height(), width()).count { hasMine(it) }

    fun hasMine(coordinate: Coordinate): Boolean {
        return cellOf(coordinate).hasMine
    }

    fun isClosed(coordinate: Coordinate): Boolean {
        return cellOf(coordinate).isClosed()
    }

    fun open(coordinate: Coordinate) {
        if (!isClosed(coordinate) || hasMine(coordinate)) {
            return
        }

        val aroundCoordinates = Coordinates.around(coordinate).filter { it.isOnBoard(height(), width()) }
        val aroundMineCount = AroundMineCount.of(aroundCoordinates.count { hasMine(it) })

        cellOf(coordinate).open(aroundMineCount)

        if (aroundMineCount.isZero()) {
            aroundCoordinates.forEach { open(it) }
        }
    }

    fun isRunning(): Boolean {
        val coordinates = Coordinates.all(height(), width())
        val closedCount = coordinates.count { isClosed(it) }
        return closedCount > mineCount
    }

    private fun cellOf(coordinate: Coordinate): Cell = rows[coordinate.row][coordinate.col]

    private fun height(): Int = rows.size

    private fun width(): Int = rows.first().size

    companion object {
        fun create(height: Int, width: Int, mineCount: Int, mineCoordinateGenerator: MineCoordinateGenerator): Board {
            val totalCellCount = height * width
            require(mineCount <= totalCellCount) { "보드 전체 칸 수($totalCellCount)보다 지뢰가 많을 수 없습니다." }

            val mineCoordinates: Set<Coordinate> = mineCoordinateGenerator.generate(mineCount)
            val rows = (Coordinate.ROW_START_POSITION until height).map { row ->
                Row.of(width, row, mineCoordinates)
            }
            return Board(rows)
        }
    }
}
