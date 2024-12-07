package minesweeper

data class Cells(val cells: List<Cell>) {
    init {
        require(cells.isNotEmpty()) { "셀은 1개 이상 이어야 합니다" }
    }

    fun placeMines(minePositions: Mines): Cells {
        cells.forEach { cell ->
            if (minePositions.cells.cells.contains(cell)) {
                cell.isMine = true
            }
        }
        return Cells(cells)
    }

    override fun toString(): String {
        return cells.joinToString("") { it.toString() }
    }

    fun print(width: Int): String {
        return cells.chunked(width).joinToString("\n") { it.joinToString(" ") }
    }

    companion object {
        fun generate(
            heightRange: IntRange,
            widthRange: IntRange,
        ): Cells {
            val cells = heightRange.flatMap { h -> (widthRange).map { w -> Cell(Height(h), Width(w)) } }
            return Cells(cells)
        }
    }
}