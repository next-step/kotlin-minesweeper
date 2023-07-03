package domain

class Board(
    private val rows: List<Row>,
    private val height: Int = rows.size,
    private val width: Int = rows.first().size,
) : List<Row> by rows {
    fun hasMine(coordinate: Coordinate): Boolean {
        return rows[coordinate.row][coordinate.col].isMine()
    }

    fun isClosed(coordinate: Coordinate): Boolean {
        return rows[coordinate.row][coordinate.col].isClosed()
    }

    fun open(coordinate: Coordinate) {
        if (!isClosed(coordinate)) {
            return
        }

        val neighborMineCount = Coordinates.neighbors(coordinate)
            .filter { it.isOnBoard(height, width) }
            .count { hasMine(it) }

        rows[coordinate.row][coordinate.col] = Cell.of(neighborMineCount)
    }

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
