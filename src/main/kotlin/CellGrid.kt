class CellGrid(val cells: List<Cell>) {
    val size: Int
        get() = cells.size

    operator fun get(index: Int): Cell {
        return cells[index]
    }

    fun plantMine(targetIndex: Int): CellGrid =
        CellGrid(cells.mapIndexed { index, cell -> if (index == targetIndex) Cell(MineStatus.MINE) else cell })

    fun updateMineCounts(
        mineCountMatrix: List<List<Int>>,
        height: Int,
        heightRange: IntRange,
        widthRange: IntRange,
    ): List<List<Int>> {
        var result = mineCountMatrix
        cells.forEachIndexed { width, cell ->
            if (cell.isMine()) {
                result = MineAreaProcessor.updateSurroundingMineCounts(result, height, width, heightRange, widthRange)
            }
        }
        return result
    }

    companion object {
        fun of(width: Int): CellGrid = CellGrid((0 until width).map { Cell(MineStatus.EMPTY) })
    }
}
