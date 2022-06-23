package com.nextstep.jngcii.minesweeper.domain

class MineManager(
    private val booleanStrategy: BooleanStrategy
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
}
