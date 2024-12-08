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

    fun updateMineCounts(): Rows = Rows(rows.map { it.updateMineCounts(rows = this) })

    companion object {
        fun ready(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Rows =
            Rows(
                rows =
                    List(height.size) {
                        Columns.ready(
                            width = width,
                            rowIndex = Index.create(value = it, maxSize = height.size),
                            element = element,
                        )
                    },
            )
    }
}
