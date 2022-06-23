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

        val mineCountsByRow = getMineCountsByRow(rowCount, mineCount)

        val mineLocationsByRow = mineCountsByRow.map {
            mineManager.selectMineLocation(columnCount, it)
        }

        return MineMap(mineLocationsByRow)
    }

    private fun getMineCountsByRow(rowCount: Int, mineCount: Int): MineCountsByRow {
        val mineCountsByRow = MineCountsByRow(rowCount)

        mineManager.assignMinesOnRow(
            mineCountsByRow = mineCountsByRow,
            rowIndex = INITIAL_ROW_ASSIGN_INDEX,
            assignableMineCount = mineCount
        )

        return mineCountsByRow
    }

    companion object {
        private const val INITIAL_ROW_ASSIGN_INDEX = 0
    }
}
