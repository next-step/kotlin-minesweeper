class CellGrid(private val cells: List<Cell>) {
    val size: Int
        get() = cells.size

    operator fun get(index: Int): Cell {
        return cells[index]
    }

    fun plantMine(targetIndex: Int): CellGrid =
        CellGrid(cells.mapIndexed { index, cell -> if (index == targetIndex) Cell(MineStatus.MINE) else cell })

    fun updateMineCountsForRow(
        minefieldMatrix: MinefieldMatrix,
        height: Int,
    ): MinefieldMatrix {
        var result = minefieldMatrix
        cells.forEachIndexed { width, cell ->
            if (cell.isMine()) result = result.updateSurroundingMineCounts(height, width)
        }
        return result
    }

    companion object {
        fun of(width: Int): CellGrid =
            CellGrid((Const.START_INDEX until width).map { Cell(MineStatus.EMPTY) })
    }
}
