package domain

class MineBoard(
    val rows: List<Row>,
) : List<Row> by rows {
    companion object {
        fun create(height: Int, width: Int, mineCount: Int, mineCoordinateGenerator: MineCoordinateGenerator): MineBoard {
            val totalCellCount = height * width
            require(mineCount <= totalCellCount) { "보드 전체 칸 수($totalCellCount)보다 지뢰가 많을 수 없습니다." }

            val mineCoordinates: Set<Coordinate> = mineCoordinateGenerator.generate(mineCount)
            val rows = (Coordinate.ROW_START_POSITION until height).map { row ->
                Row.of(width, row, mineCoordinates)
            }
            return MineBoard(rows)
        }
    }
}
