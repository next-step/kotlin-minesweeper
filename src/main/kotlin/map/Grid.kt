package map

import cell.Element

class Grid(
    val points: Rows,
) {
    fun place(
        rowIndex: Int,
        columnIndex: Int,
        element: Element,
    ) {
        points.rows[rowIndex].columns[columnIndex] = Point(Pair(rowIndex, columnIndex), element)
    }
}
