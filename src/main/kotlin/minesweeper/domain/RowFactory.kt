package minesweeper.domain

object RowFactory {
    fun createRows(width: Width, index: Int): Row {
        val cells = sortedSetOf<Cell>()
        for (x in 0 until width.value) {
            cells.add(Cell(Point(x, index), State.CELL))
        }
        return Row(cells)
    }
}
