package minesweeper.view

private const val ENTER_HEIGHT = "높이를 입력하세요"
private const val ENTER_WIDTH = "너비를 입력하세요"
private const val RE_ENTER_NUMBER_OVER_ONE = "1이상의 숫자여야 합니다"
private const val RE_ENTER_MINE_COUNT = "지뢰 개수를 다시 입력해주세요 - 지도 크기 이상의 지뢰입니다"
private const val ENTER_COUNT_OF_MINES = "지뢰는 몇 개인가요?"

object InputView {
    fun readHeight(): Int = readInput(message = ENTER_HEIGHT) { true }
    fun readWidth(): Int = readInput(message = ENTER_WIDTH) { true }

    fun readCountOfMines(height: Int, width: Int): Int =
        readInput(message = ENTER_COUNT_OF_MINES) { count -> count < height * width }

    private fun readInput(message: String = "", fitsCondition: (Int) -> Boolean): Int {
        if (message != ENTER_HEIGHT) println()
        println(message)

        var input = readLine()?.toIntOrNull()
        while (input == null || input < 1) {
            println(RE_ENTER_NUMBER_OVER_ONE)
            input = readLine()?.toIntOrNull()
        }

        while (input == null || !fitsCondition(input)) {
            println(RE_ENTER_MINE_COUNT)
            input = readLine()?.toIntOrNull()
        }

        return input
    }
}
