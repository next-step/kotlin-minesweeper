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

    fun updateMineCount(countMines: (Index?, Index?) -> Int): Rows {
        val updatedRows = rows.map { it.updatePoints(countMines) }
        return Rows(updatedRows)
    }

    fun open(
        rowsIndex: Index,
        columnIndex: Index,
    ): Rows? =
        Rows(
            rows
                .mapIndexed { index, row ->
                    if (index != rowsIndex.value) row else row.open(columnIndex = columnIndex) ?: return null
                },
        )

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
