package minesweeper.ui

class InputView {

    fun inputRowCount(): Int {
        println("높이를 입력하세요.")
        val rowCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        return if (rowCount <= 0) inputRowCount() else rowCount
    }

    fun inputColumnCount(): Int {
        println()
        println("너비를 입력하세요.")
        val columnCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        return if (columnCount <= 0) inputColumnCount() else columnCount
    }

    fun inputMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")
        val mineCount: Int = readlnOrNull()?.toIntOrNull() ?: 0
        return if (mineCount <= 0) inputMineCount() else mineCount
    }
}
