package view

object InputView {
    private const val HEIGHT_INPUT_MESSAGE = "\n높이를 입력하세요."
    private const val WIDTH_INPUT_MESSAGE = "\n너비를 입력하세요."
    private const val MINE_COUNT_INPUT_MESSAGE = "\n지뢰는 몇 개인가요?"
    private const val INPUT_OPEN_SELL_MESSAGE = "Open a cell (row, col): "

    fun readHeight(): Int {
        println(HEIGHT_INPUT_MESSAGE)
        return readln().toInt()
    }

    fun readWidth(): Int {
        println(WIDTH_INPUT_MESSAGE)
        return readln().toInt()
    }

    fun readMineCount(): Int {
        println(MINE_COUNT_INPUT_MESSAGE)
        return readln().toInt()
    }

    fun readAskOpenCellMessage(): Pair<Int, Int> {
        println(INPUT_OPEN_SELL_MESSAGE)
        return readln().split(",").map { it.trim().toInt() }.let { (row, col) -> row to col }
    }
}
