package map

import cell.Element
import mine.MineCount

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

    fun shuffle(mineCount: MineCount): List<Point> =
        points.rows
            .flatMap { it.columns }
            .shuffled()
        .take(mineCount.count)
}
