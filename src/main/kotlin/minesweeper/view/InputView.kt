package minesweeper.view

class InputView {

    fun readHeight(): Int = readInt(READ_HEIGHT_MESSAGE)

    fun readWidth(): Int = readInt(READ_WIDTH_MESSAGE)

    fun readMineQuantity(): Int = readInt(READ_MINE_QUANTITY_MESSAGE)

    fun readPosition(): String {
        print(READ_POSITION_MESSAGE)
        return readln()
    }

    private fun readInt(message: String): Int {
        println(message)
        val input = readln().toInt()
        newLine()
        return input
    }

    private fun newLine() { println() }

    companion object {
        private const val READ_HEIGHT_MESSAGE = "높이를 입력하세요."
        private const val READ_WIDTH_MESSAGE = "너비를 입력하세요."
        private const val READ_MINE_QUANTITY_MESSAGE = "지뢰 개수를 입력하세요."
        private const val READ_POSITION_MESSAGE = "open: "
    }
}
