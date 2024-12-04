package map

import cell.Cell
import cell.Element

class Rows(
    val rows: List<Columns>,
) {
    val rowSize: Int
        get() {
            return rows.size
        }

    val columnSize: Int
        get() {
            return rows[0].columns.size
        }

    companion object {
        fun ready(
            height: Height,
            width: Width,
            element: Element = Cell,
        ): Rows = Rows(rows = List(height.size) { Columns.ready(width = width, rowIndex = it.toIndex(), element = element) })
    }
}
