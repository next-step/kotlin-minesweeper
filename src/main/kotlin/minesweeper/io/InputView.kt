package minesweeper.io

object InputView {
    private const val HEIGHT_INPUT_MESSAGE = "높이를 입력하세요."
    private const val WIDTH_INPUT_MESSAGE = "높이를 입력하세요."
    private const val MINE_COUNT_INPUT_MESSAGE = "지뢰는 몇 개인가요?"
    private const val INVALID_INPUT_ERROR_MESSAGE = "숫자로 입력해주세요."
    private const val NEGATIVE_INPUT_ERROR_MESSAGE = "양수만 입력가능합니다."

    fun getHeight(): Int {
        println(HEIGHT_INPUT_MESSAGE)
        return getPositiveInput()
    }

    fun getWidth(): Int {
        println(WIDTH_INPUT_MESSAGE)
        return getPositiveInput()
    }

    fun getMineCount(): Int {
        println(MINE_COUNT_INPUT_MESSAGE)
        return getPositiveInput()
    }

    private fun getPositiveInput(): Int {
        val width = requireNotNull(readlnOrNull()?.toIntOrNull()) { INVALID_INPUT_ERROR_MESSAGE }
        println()
        require(width > 0) { NEGATIVE_INPUT_ERROR_MESSAGE }
        return width
    }
}
