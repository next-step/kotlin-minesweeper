package map

import cell.Cell

class Columns(
    val columns: MutableList<Point>,
) {
    companion object {
        fun ready(
            width: Width,
            rowIndex: Index,
        ): Columns = Columns(columns = MutableList(size = width.size) { Point(Pair(rowIndex, it.toIndex()), Cell) })
    }
}
