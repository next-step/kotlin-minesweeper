package domain

interface MinePlacer {
    fun placeMines(
        cells: Cells,
        mineCount: Int,
    ): Cells
}
