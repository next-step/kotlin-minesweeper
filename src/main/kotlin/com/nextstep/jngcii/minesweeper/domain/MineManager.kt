package com.nextstep.jngcii.minesweeper.domain

class MineManager(
    private val booleanStrategy: BooleanStrategy,
    private val pickStrategy: PickStrategy,
) {
    tailrec fun assignMinesOnRow(
        mineCountsByRow: MineCountsByRow,
        rowIndex: Int,
        assignableMineCount: Int,
        maxMineCountOnSingleRow: Int
    ) {
        if (assignableMineCount <= 0) return

        val nextRowIndex = (rowIndex + 1) % mineCountsByRow.size

        val isFullOnRow = maxMineCountOnSingleRow == mineCountsByRow[rowIndex]

        if (booleanStrategy.next() && !isFullOnRow) {
            mineCountsByRow[rowIndex] += 1
            assignMinesOnRow(mineCountsByRow, nextRowIndex, assignableMineCount - 1, maxMineCountOnSingleRow)
        } else {
            assignMinesOnRow(mineCountsByRow, nextRowIndex, assignableMineCount, maxMineCountOnSingleRow)
        }
    }

    fun setRow(
        columnCount: Int,
        mineCountOfRow: Int
    ): Row {
        val rowIndexes = List(columnCount) { it }
        val pickedIndexes = pickStrategy.take(rowIndexes, mineCountOfRow)

        val row = MutableList(columnCount) { false }
        pickedIndexes.forEach { row[it] = true }

        return Row(row)
    }
}
