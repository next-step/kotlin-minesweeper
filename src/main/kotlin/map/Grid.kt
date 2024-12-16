package map

import element.Element
import minecount.MineCount
import minecount.strategy.MineCountStrategy

class Grid(
    val rows: Rows,
    private val mineCountStrategy: MineCountStrategy,
) {
    fun place(
        rowIndex: Index?,
        columnIndex: Index?,
        element: Element,
    ) {
        val (validatedRowIndex, validatedColumnIndex) = ensureValidPosition(rowIndex, columnIndex)
        rows.columns[validatedRowIndex.value].points[validatedColumnIndex.value] =
            Point(Pair(validatedRowIndex, validatedColumnIndex), element)
    }

    fun updateMineCountByCell(): Grid {
        val updateRows =
            rows.updateMineCount { rowIndex, columnIndex -> mineCountStrategy.calculate(rowIndex, columnIndex) }

        return Grid(rows = updateRows, mineCountStrategy = mineCountStrategy)
    }

    private fun ensureValidPosition(
        rowIndex: Index?,
        columnIndex: Index?,
    ): Pair<Index, Index> {
        require(rowIndex != null && columnIndex != null) { "폭탄 설치 위치가 존재하지 않습니다." }
        require(rowIndex.value < rows.rowSize) { "폭탄 설치 행의 위치는 ${rows.rowSize}보다 작아야 합니다." }
        require(columnIndex.value < rows.columnSize) { "폭탄 설치 열의 위치는 ${rows.columnSize}보다 작아야 합니다." }

        return Pair(rowIndex, columnIndex)
    }

    fun shuffle(mineCount: MineCount): List<Point> =
        rows.columns
            .flatMap { it.points }
            .shuffled()
            .take(mineCount.count)

    fun open(
        rowIndex: Index,
        columnIndex: Index,
    ): Grid? {
        return Grid(
            rows = rows.open(rowsIndex = rowIndex, columnIndex = columnIndex) ?: return null,
            mineCountStrategy = mineCountStrategy,
        )
    }
}
