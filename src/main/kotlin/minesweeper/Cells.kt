package minesweeper

data class Cells(val cells: List<Cell>) {
    init {
        require(cells.isNotEmpty()) { "셀은 1개 이상 이어야 합니다" }
    }

    fun placeMines(minePositions: List<Cell>): Cells {
        cells.forEach { cell ->
            if (minePositions.contains(cell)) {
                cell.isMine = true
            }
        }
        return Cells(cells)
    }

    companion object {
        fun generate(
            heightRange: IntRange,
            widthRange: IntRange,
        ): Cells {
            val cells = heightRange.flatMap { height -> (widthRange).map { width -> Cell(Height(height), Width(width)) } }
            return Cells(cells)
        }
    }
}