class CellGrid(val cells: List<Cell>) {
    val size: Int
        get() = cells.size

    operator fun get(index: Int): Cell {
        return cells[index]
    }

    fun plantMine(targetIndex: Int): CellGrid =
        CellGrid(cells.mapIndexed { index, cell -> if (index == targetIndex) Cell(MineStatus.MINE) else cell })

    companion object {
        fun of(width: Int): CellGrid = CellGrid((0 until width).map { Cell(MineStatus.EMPTY) })
    }
}
