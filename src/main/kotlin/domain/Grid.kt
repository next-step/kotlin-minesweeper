package domain

class Grid(
    private val height: Height,
    private val width: Width,
    private val cells: Cells,
) {
    fun getCells(): Cells = cells

    fun withNumberHints(): Grid {
        val updatedCells = cells.addNumberHints()
        return Grid(height, width, updatedCells)
    }
}
