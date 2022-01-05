package minesweeper.view

object InputView {

    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_MINE_COUNT_MESSAGE = "지뢰는 몇 개인가요?"
    private const val INPUT_NULL_POINTER_EXCEPTION_MESSAGE = "입력은 Null일 수 없습니다."
    private const val OPEN_MESSAGE = "open: "

    fun inputHeight(): String {
        println(INPUT_HEIGHT_MESSAGE)
        return readLine() ?: throw IllegalArgumentException(INPUT_NULL_POINTER_EXCEPTION_MESSAGE)
    }

    fun inputWidth(): String {
        println(INPUT_WIDTH_MESSAGE)
        return readLine() ?: throw IllegalArgumentException(INPUT_NULL_POINTER_EXCEPTION_MESSAGE)
    }

    fun inputMineCount(): String {
        println(INPUT_MINE_COUNT_MESSAGE)
        return readLine() ?: throw IllegalArgumentException(INPUT_NULL_POINTER_EXCEPTION_MESSAGE)
    }

    fun inputOpen(): String {
        print(OPEN_MESSAGE)
        return readLine() ?: throw IllegalArgumentException(INPUT_NULL_POINTER_EXCEPTION_MESSAGE)
    }
}
