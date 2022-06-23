package com.nextstep.jngcii.minesweeper.domain

class MineManager(
    private val booleanStrategy: BooleanStrategy,
    private val pickStrategy: PickStrategy,
) {
    tailrec fun assignMinesOnRow(
        mineCountsByRow: MineCountsByRow,
        rowIndex: Int,
        assignableMineCount: Int
    ) {
        if (assignableMineCount <= 0) return

        val nextRowIndex = (rowIndex + 1) % mineCountsByRow.size

        if (booleanStrategy.next()) {
            mineCountsByRow[rowIndex] += 1
            assignMinesOnRow(mineCountsByRow, nextRowIndex, assignableMineCount - 1)
        } else {
            assignMinesOnRow(mineCountsByRow, nextRowIndex, assignableMineCount)
        }
    }

    fun selectMineLocation(
        columnCount: Int,
        mineCountOfRow: Int
    ): MineLocationByRow {
        val rowIndexes = List(columnCount) { it }
        val pickedIndexes = pickStrategy.take(rowIndexes, mineCountOfRow)

        return MineLocationByRow(pickedIndexes)
    }
}
