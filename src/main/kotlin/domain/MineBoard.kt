package domain

class MineBoard(
    val rows: List<Row>,
) : List<Row> by rows {
    companion object {
        fun init(height: Int, width: Int, mineCount: Int, minePositionGenerator: MinePositionGenerator): MineBoard {
            val minePositions: List<Position> = minePositionGenerator.generate(height, width, mineCount)

            val rows = (1..height).map { row ->
                val cells = (1..width).map { col ->
                    val isMine = minePositions.contains(Position(row, col))
                    if (isMine) Cell.MINE else Cell.CLOSED
                }
                Row(cells)
            }

            return MineBoard(rows)
        }


    }
}
