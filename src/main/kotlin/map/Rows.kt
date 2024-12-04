package map

import cell.Cell
import cell.Element

class Rows(
    val rows: List<Columns>,
) {
    companion object {
        fun ready(
            height: Height,
            width: Width,
            element: Element = Cell,
        ): Rows = Rows(rows = List(height.size) { Columns.ready(width = width, rowIndex = it.toIndex(), element = element) })
    }
}
