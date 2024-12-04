package map

import cell.Element

class Grid(
    val points: Rows,
) {
    fun place(
        rowIndex: Index,
        columnIndex: Index,
        element: Element,
    ) {
        points.rows[rowIndex.value].columns[columnIndex.value] = Point(Pair(rowIndex, columnIndex), element)
    }
}
