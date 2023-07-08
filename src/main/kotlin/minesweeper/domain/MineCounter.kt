package minesweeper.domain

object MineCounter {
    private val neighborOffsets = listOf(
        Point(-1, -1), Point(-1, 0), Point(-1, 1),
        Point(0, -1), Point(0, 1),
        Point(1, -1), Point(1, 0), Point(1, 1)
    )

    fun calculateNeighborMines(board: MineBoard) {
        val height = board.height
        val width = board.width

        (0 until height).forEach { i ->
            (0 until width).forEach { j ->
                val currentPoint = Point(i, j)
                val currentCell = board.getCell(currentPoint)
                if (currentCell.type != CellType.Mine) {
                    val mineCount = neighborOffsets.count { offset ->
                        val neighborPoint = Point(i + offset.row, j + offset.col)
                        neighborPoint.row in 0 until height &&
                            neighborPoint.col in 0 until width &&
                            board.getCell(neighborPoint).type == CellType.Mine
                    }
                    currentCell.mineCount = mineCount
                }
            }
        }
    }
}
