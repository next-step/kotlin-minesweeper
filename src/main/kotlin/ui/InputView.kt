package ui

object InputView {
    fun inputRows(): Int {
        println("높이를 입력하세요.")
        val rows: String = readLine() ?: throw IllegalArgumentException("숫자를 입력해야 합니다")

        return rows.toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력 가능합니다")
    }

    fun inputCols(): Int {
        println("너비를 입력하세요.")
        val cols: String = readLine() ?: throw IllegalArgumentException("숫자를 입력해야 합니다")

        return cols.toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력 가능합니다")
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount: String = readLine() ?: throw IllegalArgumentException("숫자를 입력해야 합니다")

        return mineCount.toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력 가능합니다")
    }
}
