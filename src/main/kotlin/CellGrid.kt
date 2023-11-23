class CellGrid(val cellCollection: List<Cell>) {
    val size: Int
        get() = cellCollection.size

    companion object {
        fun of(width: Int): CellGrid = CellGrid((0 until width).map { Cell(MineStatus.EMPTY) })
    }
}
