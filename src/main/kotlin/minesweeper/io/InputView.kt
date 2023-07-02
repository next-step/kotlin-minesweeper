package minesweeper.io

import minesweeper.domain.Point

object InputView {
    private const val HEIGHT_INPUT_MESSAGE = "높이를 입력하세요."
    private const val WIDTH_INPUT_MESSAGE = "높이를 입력하세요."
    private const val MINE_COUNT_INPUT_MESSAGE = "지뢰는 몇 개인가요?"
    private const val INVALID_INPUT_ERROR_MESSAGE = "올바르지 않은 입력입니다."
    private const val NEGATIVE_OR_ZERO_INPUT_ERROR_MESSAGE = "양수만 입력가능합니다."
    private const val NEGATIVE_INPUT_ERROR_MESSAGE = "음수는 입력 불가능 합니다."

    private const val OPEN_POINT_INPUT_MESSAGE = "open: "
    private const val INPUT_DELIMITER = ", "

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
        require(width > 0) { NEGATIVE_OR_ZERO_INPUT_ERROR_MESSAGE }
        return width
    }

    fun getOpenPoint(): Point {
        print(OPEN_POINT_INPUT_MESSAGE)

        val splits = requireNotNull(readlnOrNull()?.split(INPUT_DELIMITER)) { INVALID_INPUT_ERROR_MESSAGE }
        require(splits.size == 2) { INVALID_INPUT_ERROR_MESSAGE }
        val x = requireNotNull(splits[0].toIntOrNull()) { INVALID_INPUT_ERROR_MESSAGE }
        val y = requireNotNull(splits[1].toIntOrNull()) { INVALID_INPUT_ERROR_MESSAGE }
        require(x >= 0 && y >= 0) { NEGATIVE_INPUT_ERROR_MESSAGE }
        return Point(x, y)
    }
}
