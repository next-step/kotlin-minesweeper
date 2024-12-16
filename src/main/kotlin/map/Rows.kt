package map

import element.Cell
import element.Element

class Rows(
    val columns: List<Columns>,
) {
    val rowSize: Int
        get() {
            return columns.size
        }

    val columnSize: Int
        get() {
            return columns[0].points.size
        }

    fun updateMineCount(countMines: (Index?, Index?) -> Int): Rows {
        val updatedRows = columns.map { it.updatePoints(countMines) }
        return Rows(updatedRows)
    }

    fun open(
        rowsIndex: Index,
        columnIndex: Index,
    ): Rows? =
        Rows(
            columns
                .mapIndexed { index, column ->
                    if (index != rowsIndex.value) column else column.open(columnIndex = columnIndex) ?: return null
                },
        )

    fun isOpenAdjacent(
        rowIndex: Index,
        columnIndex: Index,
    ): Boolean = columns[rowIndex.value].isOpenAdjacent(columnIndex = columnIndex)

    companion object {
        fun ready(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Rows =
            Rows(
                columns =
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
