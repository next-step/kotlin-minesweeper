package com.nextstep.jngcii.minesweeper.domain

class MineMapFactory(
    private val mineManager: MineManager
) {
    fun create(
        rowCount: Int,
        columnCount: Int,
        mineCount: Int
    ) {
        val mineCountsByRow = getMineCountsByRow(rowCount, mineCount)

        val mineLocationsByRow = mineCountsByRow.map {
            mineManager.selectMineLocation(columnCount, it)
        }
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
