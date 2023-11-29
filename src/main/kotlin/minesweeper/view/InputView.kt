package minesweeper.view

object InputView {
    private const val HEIGHT_REQUEST_MESSAGE = "높이를 입력하세요."

    val height: Int
        get() {
            println(HEIGHT_REQUEST_MESSAGE)
            return readIntInput()
        }

    private fun readIntInput(): Int =
        readInput().toIntOrNull() ?: throw IllegalArgumentException("입력 값은 정수여야 합니다")

    private fun readInput(): String =
        readlnOrNull() ?: throw IllegalArgumentException("입력 값이 없습니다")
}
