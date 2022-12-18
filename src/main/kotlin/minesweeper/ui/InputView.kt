package minesweeper.ui

class InputView {

    fun inputColumnCount(): String {
        println("높이를 입력하세요.")
        val columnCountText: String = readlnOrNull() ?: ""
        return columnCountText.ifBlank { inputColumnCount() }
    }

    fun inputRowCount(): String {
        println()
        println("너비를 입력하세요.")
        val rowCountText: String = readlnOrNull() ?: ""
        return rowCountText.ifBlank { inputRowCount() }
    }

    fun inputMineCount(): String {
        println()
        println("지뢰는 몇 개인가요?")
        val mineCountText: String = readlnOrNull() ?: ""
        return mineCountText.ifBlank { inputMineCount() }
    }
}
