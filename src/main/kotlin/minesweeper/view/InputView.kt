package minesweeper.view

object InputView {

    private const val MESSAGE_INPUT_HEIGHT = "높이를 입력하세요."
    private const val MESSAGE_INPUT_WIDTH = "너비를 입력하세요."
    private const val MESSAGE_INPUT_MINE_COUNT = "지뢰의 수를 입력하세요."

    fun inputHeight(input: Int): Int {
        println(MESSAGE_INPUT_HEIGHT)
        return readLine()?.toIntOrNull() ?: 0
    }

    fun inputWidth(input: Int): Int {
        println(MESSAGE_INPUT_WIDTH)
        return readLine()?.toIntOrNull() ?: 0
    }

    fun inputMineCount(input: Int): Int {
        println(MESSAGE_INPUT_MINE_COUNT)
        return readLine()?.toIntOrNull() ?: 0
    }
}
