package domain

class CellList(val cells: List<Cell>) {

    companion object {
        fun createEmptyRow(row: Int, cols: Int): CellList {
            return CellList((0 until cols).map { col ->
                Cell.createEmptyCell(row, col)
            })
        }
    }
}
