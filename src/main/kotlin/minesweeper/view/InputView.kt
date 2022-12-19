package minesweeper.view

object InputView {
    const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    const val INPUT_MINE_COUNT_MESSAGE = "지뢰의 개수를 입력해주세요."
    private const val ERROR = "[ERROR] "

    fun inputNumber(): String {
        return readln()
    }

    fun printError(message: String) {
        println(ERROR + message)
    }
}
