package view

object InputView {
    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_MINE_COUNT_MESSAGE = "지뢰는 몇 개인가요?"

    fun readHeight(): Int {
        println(INPUT_HEIGHT_MESSAGE)
        return readln().toInt()
    }

    fun readWidth(): Int {
        println(INPUT_WIDTH_MESSAGE)
        return readln().toInt()
    }

    fun readMineCount(): Int {
        println(INPUT_MINE_COUNT_MESSAGE)
        return readln().toInt()
    }
}
