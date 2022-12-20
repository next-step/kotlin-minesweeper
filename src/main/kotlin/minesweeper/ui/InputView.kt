package minesweeper.ui

import minesweeper.domain.ColumnCount
import minesweeper.domain.MineCount
import minesweeper.domain.RowCount

class InputView {

    fun inputRowCount(): RowCount {
        println("높이를 입력하세요.")
        val rowCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (rowCount <= 0) return inputRowCount()
        return RowCount(rowCount)
    }

    fun inputColumnCount(): ColumnCount {
        println()
        println("너비를 입력하세요.")
        val columnCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (columnCount <= 0) return inputColumnCount()
        return ColumnCount(columnCount)
    }

    fun inputMineCount(): MineCount {
        println()
        println("지뢰는 몇 개인가요?")
        val mineCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (mineCount <= 0) return inputMineCount()
        return MineCount(mineCount)
    }
}
