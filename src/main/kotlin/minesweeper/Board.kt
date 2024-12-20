package minesweeper

class Board(
    private val dimensions: Dimensions,
    minePositions: Set<Position>,
) {
    private val cells: Cells = initializeCells(minePositions)

    init {
        require(minePositions.isNotEmpty()) { "마인은 최소 ${MIN_MINE_COUNT}개 이상 이어야 합니다." }
        require(minePositions.size < dimensions.totalCells) { "마인의 수는 전체 셀 수보다 작아야 합니다." }
    }

    fun open(openPosition: Position): OpenState {
        return cells.open(openPosition)
    }

    fun draw(): BoardDrawing {
        return BoardDrawing.create(cells)
    }

    private fun initializeCells(minePositions: Set<Position>): Cells {
        val cellList =
            dimensions.allPositions().map { position ->
                if (minePositions.contains(position)) {
                    Cell.createMine(position)
                } else {
                    Cell.createDefault(position)
                }
            }
        return Cells.detectCreateOf(cellList)
    }

    companion object {
        const val MIN_MINE_COUNT = 1
    }
}
