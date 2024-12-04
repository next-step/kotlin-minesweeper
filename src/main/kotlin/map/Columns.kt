package map

import cell.Cell

class Columns(
    val columns: MutableList<Point>,
) {
    companion object {
        fun ready(
            width: Width,
            rowIndex: Int,
        ): Columns = Columns(columns = MutableList(size = width.size) { index -> Point(Pair(rowIndex, index), Cell) })
    }
}
