package minesweeper.domain

object MineCounter {
    private val neighborOffsets = listOf(
        Point(-1, -1), Point(-1, 0), Point(-1, 1),
        Point(0, -1), Point(0, 1),
        Point(1, -1), Point(1, 0), Point(1, 1)
    )

    fun calculateNeighborMines(board: MineBoard) {
        board.forEachCell { point ->
            updateMineCountForCell(point, board)
        }
    }

    private fun updateMineCountForCell(currentPoint: Point, board: MineBoard) {
        val currentCell = board.getCell(currentPoint)
        if (currentCell is EmptyCell) {
            val mineCount = countNeighborMines(currentPoint, board)
            currentCell.mineCount = mineCount
        }
    }

    private fun countNeighborMines(currentPoint: Point, board: MineBoard): Int {
        return neighborOffsets.count { offset ->
            val neighborPoint = currentPoint + offset
            neighborPoint.isWithin(board.height, board.width) && board.getCell(neighborPoint) is MineCell
        }
    }
}
