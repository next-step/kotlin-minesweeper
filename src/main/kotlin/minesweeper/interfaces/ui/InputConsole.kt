package minesweeper.interfaces.ui

object InputConsole {
    fun queryHeight(): Int =
        try {
            println("높이를 입력하세요.")
            queryNumber()
        } catch (e: NumberFormatException) {
            println("숫자를 입력해주세요")
            queryNumber()
        }

    fun queryWidth(): Int =
        try {
            println("너비를 입력하세요.")
            queryNumber()
        } catch (e: NumberFormatException) {
            println("숫자를 입력해주세요")
            queryNumber()
        }

    fun queryMineCount(): Int =
        try {
            println("지뢰는 몇 개인가요?")
            queryNumber()
        } catch (e: NumberFormatException) {
            println("숫자를 입력해주세요")
            queryNumber()
        }

    private fun queryNumber(): Int = readln().trim().toInt()
}
