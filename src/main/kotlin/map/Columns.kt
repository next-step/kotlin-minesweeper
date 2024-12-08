package map

import cell.Cell
import cell.Element

class Columns(
    val columns: MutableList<Point>,
) {
    fun updateMineCounts(rows: Rows): Columns =
        Columns(
            columns
                .map { it.updateMineCounts(rows = rows) }
                .toMutableList(),
        )

    companion object {
        fun ready(
            width: Width,
            rowIndex: Index?,
            element: Element = Cell.ready(),
        ): Columns =
            Columns(
                columns =
                    MutableList(size = width.size) {
                        Point(
                            Pair(
                                rowIndex,
                                Index.create(value = it, maxSize = width.size),
                            ),
                            element,
                        )
                    },
            )
    }
}
