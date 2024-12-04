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
        require(rowIndex.value <= points.rows.size) { "폭탄 설치 행의 위치는 ${points.rowSize}보다 작아야합니다." }
        require(columnIndex.value <= points.rows.size) { "폭탄 설치 행의 위치는 ${points.rows[0].columns.size}보다 작아야합니다." }
        points.rows[rowIndex.value].columns[columnIndex.value] = Point(Pair(rowIndex, columnIndex), element)
    }

    fun shuffle(mineCount: MineCount): List<Point> =
        points.rows
            .flatMap { it.columns }
            .shuffled()
            .take(mineCount.count)
}
