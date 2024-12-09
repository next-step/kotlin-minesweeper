package map

import cell.Element
import mine.MineCount
import minecount.strategy.MineCountStrategy

class Grid(
    val points: Rows,
    private val mineCountStrategy: MineCountStrategy
) {
    fun place(
        rowIndex: Index?,
        columnIndex: Index?,
        element: Element,
    ) {
        val (validatedRowIndex, validatedColumnIndex) = ensureValidPosition(rowIndex, columnIndex)
        points.rows[validatedRowIndex.value].columns[validatedColumnIndex.value] =
            Point(Pair(validatedRowIndex, validatedColumnIndex), element)
    }

    fun updateMineCountByCell(): Grid {
        val updateRows =
            points.updateMineCount { rowIndex, columnIndex -> mineCountStrategy.calculate(rowIndex, columnIndex) }

        return Grid(points = updateRows, mineCountStrategy = mineCountStrategy)
    }

    private fun ensureValidPosition(
        rowIndex: Index?,
        columnIndex: Index?,
    ): Pair<Index, Index> {
        require(rowIndex != null && columnIndex != null) { "폭탄 설치 위치가 존재하지 않습니다." }
        require(rowIndex.value < points.rowSize) { "폭탄 설치 행의 위치는 ${points.rowSize}보다 작아야 합니다." }
        require(columnIndex.value < points.columnSize) { "폭탄 설치 열의 위치는 ${points.columnSize}보다 작아야 합니다." }

        return Pair(rowIndex, columnIndex)
    }

    fun shuffle(mineCount: MineCount): List<Point> =
        points.rows
            .flatMap { it.columns }
            .shuffled()
            .take(mineCount.count)
}
