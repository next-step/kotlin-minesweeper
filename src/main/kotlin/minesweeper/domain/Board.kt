package minesweeper.domain

class Board(width: Width, height: Height) : Cells(width, height) {
    fun placeMines(mineCount: MineCount) {
        takeMinePositions(mineCount).forEach {
            setCellStatus(it, CellState.MINE)
        }
    }

    private fun takeMinePositions(mineCount: MineCount): List<Location> {
        return columnRange().flatMap { x ->
            rowRange().map { y ->
                Location(x, y)
            }
        }
            .shuffled()
            .take(mineCount.count)
    }

    private fun columnRange(): IntRange = 0..<width.value

    private fun rowRange(): IntRange = 0..<height.value
}
