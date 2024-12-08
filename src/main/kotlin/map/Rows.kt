package map

import cell.Cell
import cell.Element
import map.move.Position

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

    fun isContainMine(position: Position): Boolean {
        if (position.row == null || position.column == null) {
            return false
        }

        return rows
            .getOrNull(position.row.value)
            ?.columns
            ?.getOrNull(position.column.value)
            ?.isMine() ?: false
    }

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
