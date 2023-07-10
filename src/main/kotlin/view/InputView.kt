package view

import domain.Location

object InputView {

    private const val HEIGHT_STRING = "높이를 입력하세요."
    private const val WIDTH_STRING = "\n너비를 입력하세요."
    private const val COUNT_STRING = "\n지뢰는 몇 개인가요?"
    private const val OPEN_STRING = "open: "
    private const val OPEN_VALUE_EXCEPTION = "open할 x,y값을 잘못입력하였습니다"
    private const val OPEN_VALUE_DELIMITER = ","

    fun getHeight(): Int {
        println(HEIGHT_STRING)
        return readln().toInt()
    }

    fun getWidth(): Int {
        println(WIDTH_STRING)
        return readln().toInt()
    }

    fun getMineCounts(): Int {
        println(COUNT_STRING)
        return readln().toInt()
    }

    fun getOpeningLocation(height: Int, width: Int): Location {
        print(OPEN_STRING)
        val input = readln().split(OPEN_VALUE_DELIMITER).map { it.trim().toInt() }
        require(input.size == 2) { OPEN_VALUE_EXCEPTION }
        val x = input[0]
        val y = input[1]
        require(x < width) { "x의 값은 너비보다 작아야 합니다" }
        require(y < height) { "y의 값은 높이보다 작아야 합니다" }
        return Location(x, y)
    }
}
