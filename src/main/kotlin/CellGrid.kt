class CellGrid(private val cellCollection: List<Cell>) {
    operator fun get(index: Int): Cell {
        return cellCollection[index]
    }

    fun plantMine(targetIndex: Int): CellGrid =
        CellGrid(
            cellCollection.mapIndexed { index, cell ->
                if (index == targetIndex) {
                    Cell(MineStatus.MINE)
                } else {
                    cell
                }
            }
        )

    val size: Int
        get() = cellCollection.size

    companion object {
        fun of(width: Int): CellGrid = CellGrid((0 until width).map { Cell(MineStatus.EMPTY) })
    }
}
