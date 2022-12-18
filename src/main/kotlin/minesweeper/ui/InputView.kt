package minesweeper.ui

class InputView {

    fun inputRowCount(): Int {
        println("높이를 입력하세요.")
        val rowCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (rowCount <= 0) return inputRowCount()
        return rowCount
    }

    fun inputColumnCount(): Int {
        println()
        println("너비를 입력하세요.")
        val columnCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (columnCount <= 0) return inputColumnCount()
        return columnCount
    }

    fun inputMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")
        val mineCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        if (mineCount <= 0) return inputMineCount()
        return mineCount
    }
}
