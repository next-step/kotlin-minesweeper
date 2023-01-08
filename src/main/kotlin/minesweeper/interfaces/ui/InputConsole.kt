package minesweeper.interfaces.ui

object InputConsole {
    fun queryHeight(): Int =
        queryNumber {
            println("높이를 입력하세요.")
        }

    fun queryWidth(): Int =
        queryNumber {
            println("너비를 입력하세요.")
        }

    fun queryMineCount(): Int =
        queryNumber {
            println("지뢰는 몇 개인가요?")
        }

    private fun queryNumber(lambda: () -> Unit): Int {
        return try {
            lambda.invoke()
            queryNumber()
        } catch (e: NumberFormatException) {
            println("숫자를 입력해주세요")
            queryNumber { }
        }
    }

    private fun queryNumber(): Int = readln().trim().toInt()
}
