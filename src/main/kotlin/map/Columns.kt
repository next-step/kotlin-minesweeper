package map

import cell.Cell
import cell.Element

class Columns(
    val columns: MutableList<Point>,
) {
    companion object {
        fun ready(
            width: Width,
            rowIndex: Index,
            element: Element = Cell,
        ): Columns = Columns(columns = MutableList(size = width.size) { Point(Pair(rowIndex, it.toIndex()), element) })
    }
}
