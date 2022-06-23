package com.nextstep.jngcii.minesweeper.domain

class MineMapFactory(
    private val mineManager: MineManager
) {
    fun create(
        rowCount: Int,
        columnCount: Int,
        mineCount: Int
    ): MineMap {
        val totalCount = rowCount * columnCount
        require(totalCount >= mineCount) {
            "지뢰 갯수는 $totalCount($rowCount X $columnCount) 보다 클 수 없습니다. (입력값 : $mineCount)"
        }

        val mineCountsByRow = getMineCountsByRow(rowCount, columnCount, mineCount)

        val rows = mineCountsByRow.map {
            mineManager.setRow(columnCount, it)
        }

        return MineMap(rows)
    }

    private fun getMineCountsByRow(rowCount: Int, columnCount: Int, mineCount: Int): MineCountsByRow {
        val mineCountsByRow = MineCountsByRow(rowCount)

        mineManager.assignMinesOnRow(
            mineCountsByRow = mineCountsByRow,
            rowIndex = INITIAL_ROW_ASSIGN_INDEX,
            assignableMineCount = mineCount,
            maxMineCountOnSingleRow = columnCount
        )

        return mineCountsByRow
    }

    companion object {
        private const val INITIAL_ROW_ASSIGN_INDEX = 0
    }
}
