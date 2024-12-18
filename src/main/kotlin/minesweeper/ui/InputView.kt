package minesweeper.ui

class InputView(val inputProvider: () -> String? = { readln() }) {
    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readToInt()
    }

    fun readHeight(): Int {
        println("높이를 입력하세요.")

        return readToInt()
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")

        return readToInt()
    }

    private fun readToInt(): Int {
        return inputProvider()?.toIntOrNull() ?: throw IllegalArgumentException("입력이 없습니다.")
    }
}
