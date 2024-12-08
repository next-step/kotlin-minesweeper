package map

import cell.Element
import map.move.Position
import mine.MineCount

class Grid(
    val points: Rows,
) {
    fun place(
        rowIndex: Index?,
        columnIndex: Index?,
        element: Element,
    ) {
        // TODO validation 메서드 추출
        require(rowIndex != null && columnIndex != null) { "폭탄 설치 위치가 존재하지 않습니다. "}
        require(rowIndex.value <= points.rows.size) { "폭탄 설치 행의 위치는 ${points.rowSize}보다 작아야합니다." }
        require(columnIndex.value <= points.rows.size) { "폭탄 설치 행의 위치는 ${points.columnSize}보다 작아야합니다." }
        points.rows[rowIndex.value].columns[columnIndex.value] = Point(Pair(rowIndex, columnIndex), element)
    }

    fun shuffle(mineCount: MineCount): List<Point> =
        points.rows
            .flatMap { it.columns }
            .shuffled()
            .take(mineCount.count)

    fun isMine(position: Position): Boolean = points.isContainMine(position = position)
}
