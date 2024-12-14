package minesweeper.ui

object InputView {
    private const val HEIGHT_PROMPT = "높이를 입력하세요."
    private const val WIDTH_PROMPT = "너비를 입력하세요."
    private const val MINES_PROMPT = "지뢰의 개수를 입력하세요."
    private val NEWLINE = System.lineSeparator()

    fun getHeight(): Int {
        println(HEIGHT_PROMPT)
        return getInt()
    }

    fun getWidth(): Int {
        println(NEWLINE + WIDTH_PROMPT)
        return getInt()
    }

    fun getMineCount(): Int {
        println(NEWLINE + MINES_PROMPT)
        return getInt()
    }

    private fun getInt(): Int =
        readln().toIntOrNull() ?: run {
            println("숫자를 입력해주세요.")
            getInt()
        }
}
