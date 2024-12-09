package minesweeper.entity

class MineField private constructor(
    val height: Height,
    val width: Width,
    private val _cells: Cells,
) {
    val cells: List<Cell>
        get() = _cells.cells.toList()

    fun findCell(coordinate: Coordinate): Cell {
        return _cells.findCell(coordinate)
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            mineCount: MineCount,
        ): MineField {
            val generatedCells = Cells.generate(height, width, mineCount)
            return MineField(height, width, generatedCells)
        }
    }
}
