package domain

class MineBoard(
    val rows: List<Row>,
) : List<Row> by rows {
    companion object {
        fun create(height: Int, width: Int, mineCount: Int, mineCoordinateGenerator: MineCoordinateGenerator): MineBoard {
            val mineCoordinates: Set<Coordinate> = mineCoordinateGenerator.generate(mineCount)
            val rows = (Coordinate.ROW_START_POSITION..height).map { row ->
                Row.of(width, row, mineCoordinates)
            }
            return MineBoard(rows)
        }
    }
}
